package com.onlineBankingApplication.dao;

import org.springframework.data.repository.CrudRepository;

import com.onlineBankingApplication.domain.SavingAccount;

public interface SavingAccountDao extends CrudRepository<SavingAccount, Long> {

	SavingAccount findByAccountNumber(int accountNumber);

}
