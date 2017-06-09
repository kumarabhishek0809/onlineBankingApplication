
package com.onlineBankingApplication.jms;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.support.destination.BeanFactoryDestinationResolver;
import org.springframework.jms.support.destination.DestinationResolver;

@Configuration
@EnableJms
public class SpringJMSConfig {

	@Autowired
	private BeanFactory springContextBeanFactory;

	@Bean
	public JmsListenerContainerFactory<?> containerFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		//factory.setDestinationResolver(destinationResolver());
		factory.setConcurrency("3-10");
		return factory;
	}
//
//	@Bean
//	public DestinationResolver destinationResolver() {
//		return new BeanFactoryDestinationResolver(springContextBeanFactory);
//	}
//
//	@Bean
//	public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) throws JMSException {
//		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
//		jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
//		return jmsTemplate;
//	}

	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	/*
	 * 
	 * public static void main(String[] args) { // Launch the application
	 * ConfigurableApplicationContext context =
	 * SpringApplication.run(SpringJMSConfig.class, args);
	 * 
	 * JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
	 * 
	 * // Send a message with a POJO - the template reuse the message converter
	 * System.out.println("Sending an email message.");
	 * jmsTemplate.convertAndSend("mailBoxDestination", new
	 * Email("info@example.com", "Hello")); }
	 * 
	 */

}
