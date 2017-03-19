package com.onlineBankingApplication.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.onlineBankingApplication.domain.SavingTransaction;

public interface SavingTransactionDao extends CrudRepository<SavingTransaction, Long> {
	List<SavingTransaction> findAll();
}
