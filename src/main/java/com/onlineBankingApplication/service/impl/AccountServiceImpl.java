package com.onlineBankingApplication.service.impl;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineBankingApplication.dao.PrimaryAccountDao;
import com.onlineBankingApplication.dao.SavingAccountDao;
import com.onlineBankingApplication.domain.PrimaryAccount;
import com.onlineBankingApplication.domain.PrimaryTransaction;
import com.onlineBankingApplication.domain.SavingsAccount;
import com.onlineBankingApplication.domain.SavingTransaction;
import com.onlineBankingApplication.domain.User;
import com.onlineBankingApplication.service.AccountService;
import com.onlineBankingApplication.service.TransactionService;
import com.onlineBankingApplication.service.UserService;

@Service
public class AccountServiceImpl implements AccountService {

	private static final String SAVINGS = "Savings";

	private static final String PRIMARY = "Primary";

	@Autowired
	private PrimaryAccountDao primaryAccountDao;
	@Autowired
	private SavingAccountDao savingAccountDao;

	@Autowired
	private UserService userService;


	
	@Autowired
	private TransactionService transactionService;

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
	public SavingsAccount createSavingAccount() {
		SavingsAccount savingAccount = new SavingsAccount();
		savingAccount.setAccountBalance(new BigDecimal(0.0));
		savingAccount.setAccountNumber(accountGen());
		savingAccountDao.save(savingAccount);
		return savingAccountDao.findByAccountNumber(savingAccount.getAccountNumber());
	}

	@Override
	public void deposit(String accountType, double parseDouble, Principal principal) {
		User user = userService.findByUserName(principal.getName());
		if (PRIMARY.equalsIgnoreCase(accountType)) {
			PrimaryAccount primaryAccount = user.getPrimaryAccount();
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(parseDouble)));
			primaryAccountDao.save(primaryAccount);

			PrimaryTransaction primaryTransaction = new PrimaryTransaction(new Date(), "Deposit to Primary Account",
					"Account", "Finished", parseDouble, primaryAccount.getAccountBalance(), primaryAccount);
			transactionService.savePrimaryDepositTransaction(primaryTransaction);

		} else if (SAVINGS.equalsIgnoreCase(accountType)) {
			SavingsAccount savingAccount = user.getSavingsAccount();
			savingAccount.setAccountBalance(savingAccount.getAccountBalance().add(new BigDecimal(parseDouble)));
			savingAccountDao.save(savingAccount);

			SavingTransaction savingTransaction = new SavingTransaction(new Date(), "Deposit to savings Account",
					"Account", "Finished", parseDouble, savingAccount.getAccountBalance(), savingAccount);
			transactionService.saveSavingsDepositTransaction(savingTransaction);

		}
	}

	@Override
	public void withdraw(String accountType, double parseDouble, Principal principal) {
		User user = userService.findByUserName(principal.getName());
		if (PRIMARY.equalsIgnoreCase(accountType)) {
			PrimaryAccount primaryAccount = user.getPrimaryAccount();
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(parseDouble)));
			primaryAccountDao.save(primaryAccount);

			PrimaryTransaction primaryTransaction = new PrimaryTransaction(new Date(), "Withdraw to Primary Account",
					"Account", "Finished", parseDouble, primaryAccount.getAccountBalance(), primaryAccount);
			transactionService.savePrimaryWithdrawTransaction(primaryTransaction);

		} else if (SAVINGS.equalsIgnoreCase(accountType)) {
			SavingsAccount savingAccount = user.getSavingsAccount();
			savingAccount.setAccountBalance(savingAccount.getAccountBalance().subtract(new BigDecimal(parseDouble)));
			savingAccountDao.save(savingAccount);

			SavingTransaction savingTransaction = new SavingTransaction(new Date(), "Withdraw to savings Account",
					"Account", "Finished", parseDouble, savingAccount.getAccountBalance(), savingAccount);
			transactionService.saveSavingsWithdrawTransaction(savingTransaction);

		}
		
	}

}
