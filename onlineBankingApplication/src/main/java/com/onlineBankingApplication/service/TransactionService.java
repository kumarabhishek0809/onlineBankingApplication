package com.onlineBankingApplication.service;

import java.security.Principal;
import java.util.List;

import com.onlineBankingApplication.domain.PrimaryAccount;
import com.onlineBankingApplication.domain.PrimaryTransaction;
import com.onlineBankingApplication.domain.Recipient;
import com.onlineBankingApplication.domain.SavingsAccount;
import com.onlineBankingApplication.domain.SavingsTransaction;

public interface TransactionService {

	void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);

	void saveSavingsDepositTransaction(SavingsTransaction savingTransaction);
	void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction);
	void saveSavingsWithdrawTransaction(SavingsTransaction savingTransaction);

	List<PrimaryTransaction> findPrimaryTransactionList(String name);

	List<SavingsTransaction> findSavingsTransactionList(String name);

	void betweenAccountsTransafer(String transferFrom, String transferTo, String amount, PrimaryAccount primaryAccount,
			SavingsAccount savingAccount,Principal principal);

	List<Recipient> findRecipientList(Principal principal);

	Recipient saveRecipient(Recipient recipient);

	Recipient findRecipientByName(String name);

	void deleteRecipientByName(String name);

	void toSomeoneElseTransfer(Recipient recipient, String accountType, String amount, PrimaryAccount primaryAccount,
			SavingsAccount savingAccount);

}
