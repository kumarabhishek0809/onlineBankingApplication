package com.onlineBankingApplication.dao;

import org.springframework.data.repository.CrudRepository;

import com.onlineBankingApplication.entity.Recipient;

public interface RecipientDao extends CrudRepository<Recipient, Long>{

	Recipient findByName(String recipientName);

	void deleteByName(String recipientName);
	

}
