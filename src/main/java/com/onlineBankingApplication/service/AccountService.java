package com.onlineBankingApplication.service;

import java.security.Principal;

import com.onlineBankingApplication.domain.PrimaryAccount;
import com.onlineBankingApplication.domain.SavingAccount;

public interface AccountService {

	PrimaryAccount createPrimaryAccount();

	SavingAccount createSavingAccount();

	void deposit(String accountType, double parseDouble, Principal principal);

	void withdraw(String accountType, double parseDouble, Principal principal);

}
