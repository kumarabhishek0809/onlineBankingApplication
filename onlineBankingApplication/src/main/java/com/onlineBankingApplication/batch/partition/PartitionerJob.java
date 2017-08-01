package com.onlineBankingApplication.batch.partition;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Configuration
@EnableBatchProcessing
public class PartitionerJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private DataSource dataSource;

	// Partition Job
	@Bean
	public Job partitionJob() {
		return jobBuilderFactory.get("PartitionJob").incrementer(new RunIdIncrementer()).start(masterStep()) 
				.next(step2()).build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2").tasklet(dummyTask()).build();
	}

	@Bean
	public DummyTasklet dummyTask() {
		return new DummyTasklet();
	}

	@Bean
	public Step masterStep() {
		return stepBuilderFactory.get("masterStep").partitioner(slave().getName(), rangePartitioner())
				.partitionHandler(masterSlaveHandler()).build();
	}

	@Bean
	public PartitionHandler masterSlaveHandler() {
		TaskExecutorPartitionHandler handler = new TaskExecutorPartitionHandler();
		handler.setGridSize(10);
		handler.setTaskExecutor(taskExecutor());
		handler.setStep(slave());
		try {
			handler.afterPropertiesSet();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return handler;
	}

	@Bean(name = "slave")
	public Step slave() {
		System.out.println("...........called slave .........");
		return stepBuilderFactory.get("slave").<User, User>chunk(100)
				.reader(slaveReader(null, null, null))
				.processor(slaveProcessor(null))
				.writer(slaveWriter(null, null))
				.build();
	}

	@Bean
	public RangePartitioner rangePartitioner() {
		return new RangePartitioner();
	}

	@Bean
	public SimpleAsyncTaskExecutor taskExecutor() {
		return new SimpleAsyncTaskExecutor();
	}

	@Bean
	@StepScope
	public PersonItemPartitionProcessor slaveProcessor(@Value("#{stepExecutionContext[name]}") String name) {
		System.out.println("********called slave processor **********");
		PersonItemPartitionProcessor personItemPartitionProcessor = new PersonItemPartitionProcessor();
		personItemPartitionProcessor.setThreadName(name);
		return personItemPartitionProcessor;
	}

	@Bean
	@StepScope
	public JdbcPagingItemReader<User> slaveReader(@Value("#{stepExecutionContext[fromId]}") final String fromId,
			@Value("#{stepExecutionContext[toId]}") final String toId,
			@Value("#{stepExecutionContext[name]}") final String name) {
		System.out.println("slaveReader start " + fromId + " " + toId);
		JdbcPagingItemReader<User> reader = new JdbcPagingItemReader<>();
		reader.setDataSource(dataSource);
		reader.setQueryProvider(queryProvider());
		Map<String, Object> parameterValues = new HashMap<>();
		parameterValues.put("fromId", fromId);
		parameterValues.put("toId", toId);
		System.out.println("Parameter Value " + name + " " + parameterValues);
		reader.setParameterValues(parameterValues);
		reader.setPageSize(1000);
		reader.setRowMapper(new BeanPropertyRowMapper<User>() {
			{
				setMappedClass(User.class);
			}
		});
		System.out.println("slaveReader end " + fromId + " " + toId);
		return reader;
	}

	@Bean
	public PagingQueryProvider queryProvider() {
		System.out.println("queryProvider start ");
		SqlPagingQueryProviderFactoryBean provider = new SqlPagingQueryProviderFactoryBean();
		provider.setDataSource(dataSource);
		provider.setSelectClause("select id, username, password, age");
		provider.setFromClause("from partitionjobtable");
		provider.setWhereClause("where id >= :fromId and id <= :toId");
		provider.setSortKey("id");
		System.out.println("queryProvider end ");
		try {
			return provider.getObject();
		} catch (Exception e) {
			System.out.println("queryProvider exception ");
			e.printStackTrace();
		}

		return null;
	}

	@Bean
	@StepScope
	public FlatFileItemWriter<User> slaveWriter(@Value("#{stepExecutionContext[fromId]}") final String fromId,
			@Value("#{stepExecutionContext[toId]}") final String toId) {
		FlatFileItemWriter<User> reader = new FlatFileItemWriter<>();
		reader.setResource(new FileSystemResource("csv/outputs/users.processed" + fromId + "-" + toId + ".csv"));
		// reader.setAppendAllowed(false);
		reader.setLineAggregator(new DelimitedLineAggregator<User>() {
			{
				setDelimiter(",");
				setFieldExtractor(new BeanWrapperFieldExtractor<User>() {
					{
						setNames(new String[] { "id", "username", "password", "age" });
					}
				});
			}
		});
		return reader;
	}
}
