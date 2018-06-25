package com.onlineBankingApplication.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.onlineBankingApplication.entity.PrimaryTransaction;

public interface PrimaryTransactionDao extends CrudRepository<PrimaryTransaction, Long> {

	List<PrimaryTransaction> findAll();
}
