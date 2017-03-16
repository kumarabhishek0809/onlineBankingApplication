package com.onlineBankingApplication.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.onlineBankingApplication.domain.PrimaryTransaction;

public interface PrimaryTransactionDao extends CrudRepository<PrimaryTransaction, Long> {

	List<PrimaryTransaction> findAll();
}
