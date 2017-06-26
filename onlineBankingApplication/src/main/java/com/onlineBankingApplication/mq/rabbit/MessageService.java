package com.onlineBankingApplication.mq.rabbit;

import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendMessage(Map<String, String> message) {
		rabbitTemplate.convertAndSend(SpringBootRabbitMQApplication.ONLINE_RABBIT_MESSAGE_QUEUE, message);
		
	}
}
