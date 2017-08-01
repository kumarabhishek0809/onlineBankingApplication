package com.onlineBankingApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OnlineBankingApplication {
	private static final Logger LOG = LoggerFactory.getLogger(OnlineBankingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OnlineBankingApplication.class, args);
	}
}
