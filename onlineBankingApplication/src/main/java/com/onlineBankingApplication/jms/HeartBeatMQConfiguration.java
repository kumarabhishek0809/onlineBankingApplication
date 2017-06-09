package com.onlineBankingApplication.jms;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class HeartBeatMQConfiguration {

	@Autowired
	JmsTemplate jmsTemplate;

	@Value("${jms.mail.queue}")
	private String destination;

	@PostConstruct
	public void initActiveMQ() {
		System.out.println("Sending an email message.");
		jmsTemplate.convertAndSend(destination, new Email("info@example.com", "Hello"));
	}
}
