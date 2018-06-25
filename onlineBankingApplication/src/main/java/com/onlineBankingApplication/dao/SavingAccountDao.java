package com.onlineBankingApplication.dao;

import org.springframework.data.repository.CrudRepository;

import com.onlineBankingApplication.entity.SavingsAccount;

public interface SavingAccountDao extends CrudRepository<SavingsAccount, Long> {

	SavingsAccount findByAccountNumber(int accountNumber);

}
