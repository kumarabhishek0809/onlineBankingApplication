package com.onlineBankingApplication.jms;

import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * This is the queue listener class, its receiveMessage() method ios invoked
 * with the message as the parameter.
 */
@Component
public class RabbitMessageListener {

	public void receiveMessage(Map<String, String> message) {
		System.out.println(message);
	}
}
