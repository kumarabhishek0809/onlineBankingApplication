package com.onlineBankingApplication.service;

import java.security.Principal;

import com.onlineBankingApplication.entity.PrimaryAccount;
import com.onlineBankingApplication.entity.SavingsAccount;

public interface AccountService {

	PrimaryAccount createPrimaryAccount();

	SavingsAccount createSavingAccount();

	void deposit(String accountType, double parseDouble, Principal principal);

	void withdraw(String accountType, double parseDouble, Principal principal);

}
