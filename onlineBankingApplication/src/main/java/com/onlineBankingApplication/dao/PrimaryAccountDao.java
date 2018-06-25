package com.onlineBankingApplication.dao;

import org.springframework.data.repository.CrudRepository;

import com.onlineBankingApplication.entity.PrimaryAccount;

public interface PrimaryAccountDao extends  CrudRepository<PrimaryAccount, Long> {

	PrimaryAccount findByAccountNumber(int accountNumber);

}
