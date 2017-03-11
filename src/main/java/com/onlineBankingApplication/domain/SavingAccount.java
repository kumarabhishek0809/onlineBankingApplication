package com.onlineBankingApplication.domain;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.MultilineRecursiveToStringStyle;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class SavingAccount {
	private Long id;
	private int accountNumber;
	private BigDecimal accountBalance;
	
	private List<SavingTransaction> savingTransactions;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public List<SavingTransaction> getSavingTransactions() {
		return savingTransactions;
	}

	public void setSavingTransactions(List<SavingTransaction> savingTransactions) {
		this.savingTransactions = savingTransactions;
	}
	
	@Override
	public String toString() {
		return new ReflectionToStringBuilder(this, new MultilineRecursiveToStringStyle()).toString();
	}

}
