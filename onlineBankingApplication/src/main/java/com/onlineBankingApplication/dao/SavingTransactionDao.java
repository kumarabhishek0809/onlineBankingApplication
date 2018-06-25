package com.onlineBankingApplication.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.onlineBankingApplication.entity.SavingsTransaction;

public interface SavingTransactionDao extends CrudRepository<SavingsTransaction, Long> {
	List<SavingsTransaction> findAll();
}
