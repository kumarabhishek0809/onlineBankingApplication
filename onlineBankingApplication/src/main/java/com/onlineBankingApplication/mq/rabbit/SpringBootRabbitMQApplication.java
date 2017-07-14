package com.onlineBankingApplication.mq.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootRabbitMQApplication {

	@Value("${spring.rabbit.amqp.exchange}")
	private String onlineRabbitMessageExchange;

	@Value("${spring.rabbit.amqp.queue}")
	private String onlineRabbitMessageQueue;

	@Bean
	Queue queue() {
		return new Queue(onlineRabbitMessageQueue, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(onlineRabbitMessageExchange);
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(onlineRabbitMessageQueue);
	}

	@Bean
	public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListener consumer,
			@Value("${spring.rabbit.amqp.queue}") String queueName) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(consumer);
		return container;
	}

}
