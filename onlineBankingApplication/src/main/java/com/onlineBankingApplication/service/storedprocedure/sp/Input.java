package com.onlineBankingApplication.service.storedprocedure.sp;

public interface Input {

	class AccountNumberInput implements Input {
		int accountNumber;
		public AccountNumberInput(int accountNumber){
			this.accountNumber = accountNumber;
		}
		public int getAccountNumber() {
			return accountNumber;
		}
	}
}


