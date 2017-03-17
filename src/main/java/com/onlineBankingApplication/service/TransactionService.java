package com.onlineBankingApplication.service;

import java.security.Principal;
import java.util.List;

import com.onlineBankingApplication.domain.PrimaryAccount;
import com.onlineBankingApplication.domain.PrimaryTransaction;
import com.onlineBankingApplication.domain.SavingAccount;
import com.onlineBankingApplication.domain.SavingTransaction;

public interface TransactionService {

	void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);

	void saveSavingsDepositTransaction(SavingTransaction savingTransaction);
	void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction);
	void saveSavingsWithdrawTransaction(SavingTransaction savingTransaction);

	List<PrimaryTransaction> findPrimaryTransactionList(String name);

	List<SavingTransaction> findSavingsTransactionList(String name);

	void betweenAccountsTransafer(String transferFrom, String transferTo, String amount, PrimaryAccount primaryAccount,
			SavingAccount savingAccount,Principal principal);

}
