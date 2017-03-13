package com.onlineBankingApplication.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.onlineBankingApplication.dao.PrimaryAccountDao;
import com.onlineBankingApplication.dao.SavingAccountDao;
import com.onlineBankingApplication.domain.PrimaryAccount;
import com.onlineBankingApplication.domain.SavingAccount;
import com.onlineBankingApplication.service.AccountService;

public class AccountServiceImpl implements AccountService {

	@Autowired
	private PrimaryAccountDao primaryAccountDao;

	@Autowired
	private SavingAccountDao savingAccountDao;

	private static int nextAccountGenNumber = 11112232;

	@Override
	public PrimaryAccount createPrimaryAccount() {
		PrimaryAccount primaryAccount = new PrimaryAccount();
		primaryAccount.setAccountBalance(new BigDecimal(0.0));
		primaryAccount.setAccountNumber(accountGen());
		primaryAccountDao.save(primaryAccount);
		return primaryAccountDao.findByAccountNumber(primaryAccount.getAccountNumber());
	}

	private int accountGen() {
		return ++nextAccountGenNumber;
	}

	@Override
	public SavingAccount createSavingAccount() {
		SavingAccount savingAccount = new SavingAccount();
		savingAccount.setAccountBalance(new BigDecimal(0.0));
		savingAccount.setAccountNumber(accountGen());
		savingAccountDao.save(savingAccount);
		return savingAccountDao.findByAccountNumber(savingAccount.getAccountNumber());
	}

}
