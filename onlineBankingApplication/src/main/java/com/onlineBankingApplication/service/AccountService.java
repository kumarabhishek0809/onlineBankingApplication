package com.onlineBankingApplication.service;

import java.security.Principal;

import com.onlineBankingApplication.domain.PrimaryAccount;
import com.onlineBankingApplication.domain.SavingsAccount;

public interface AccountService {

	PrimaryAccount createPrimaryAccount();

	SavingsAccount createSavingAccount();

	void deposit(String accountType, double parseDouble, Principal principal);

	void withdraw(String accountType, double parseDouble, Principal principal);

}
