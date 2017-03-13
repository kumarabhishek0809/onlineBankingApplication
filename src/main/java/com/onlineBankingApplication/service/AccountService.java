package com.onlineBankingApplication.service;

import com.onlineBankingApplication.domain.PrimaryAccount;
import com.onlineBankingApplication.domain.SavingAccount;

public interface AccountService {

	PrimaryAccount createPrimaryAccount();

	SavingAccount createSavingAccount();

}
