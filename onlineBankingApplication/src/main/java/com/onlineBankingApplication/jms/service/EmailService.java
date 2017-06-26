package com.onlineBankingApplication.jms.service;

import org.springframework.stereotype.Service;

import com.onlineBankingApplication.jms.domain.Email;

@Service
public class EmailService {

	public void createNew(Email email) {
		System.out.println("createNew Received <" + email + ">");
		
	}

	public void update(Email email) {
		System.out.println("update Received <" + email + ">");
		
	}

	public void delete(Email email) {
		System.out.println("delete Received <" + email + ">");
		
	}

}
