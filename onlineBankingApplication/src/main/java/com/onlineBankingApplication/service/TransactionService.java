package com.onlineBankingApplication.service;

import java.security.Principal;
import java.util.List;

import com.onlineBankingApplication.entity.PrimaryAccount;
import com.onlineBankingApplication.entity.PrimaryTransaction;
import com.onlineBankingApplication.entity.Recipient;
import com.onlineBankingApplication.entity.SavingsAccount;
import com.onlineBankingApplication.entity.SavingsTransaction;

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
