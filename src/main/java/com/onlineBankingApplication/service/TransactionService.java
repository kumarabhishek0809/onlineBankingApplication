package com.onlineBankingApplication.service;

import com.onlineBankingApplication.domain.PrimaryTransaction;
import com.onlineBankingApplication.domain.SavingTransaction;

public interface TransactionService {

	void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);

	void saveSavingsDepositTransaction(SavingTransaction savingTransaction);

}
