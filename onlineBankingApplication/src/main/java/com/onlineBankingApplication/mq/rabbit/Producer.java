package com.onlineBankingApplication.mq.rabbit;

import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Producer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMessage(String exchange,
			String routingKey,Map<String, String> message) {
		rabbitTemplate.convertAndSend(exchange,routingKey, message);
		
	}
}
