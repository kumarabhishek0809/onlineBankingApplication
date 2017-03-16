package com.onlineBankingApplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineBankingApplication.dao.PrimaryTransactionDao;
import com.onlineBankingApplication.dao.SavingTransactionDao;
import com.onlineBankingApplication.domain.PrimaryTransaction;
import com.onlineBankingApplication.domain.SavingTransaction;
import com.onlineBankingApplication.domain.User;
import com.onlineBankingApplication.service.TransactionService;
import com.onlineBankingApplication.service.UserService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private UserService userService;

	@Autowired
	private PrimaryTransactionDao primaryTransactionDao;

	@Autowired
	private SavingTransactionDao savingTransactionDao;

	public List<PrimaryTransaction> findPrimaryTransactionList(String username) {
		User user = userService.findByUserName(username);
		return user.getPrimaryAccount().getPrimaryTransactions();
	}

	public List<SavingTransaction> findSavingTransactionList(String username) {
		User user = userService.findByUserName(username);
		return user.getSavingAccount().getSavingTransactions();
	}

	@Override
	public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction) {
		primaryTransactionDao.save(primaryTransaction);
	}

	@Override
	public void saveSavingsDepositTransaction(SavingTransaction savingTransaction) {
		savingTransactionDao.save(savingTransaction);
	}

}
