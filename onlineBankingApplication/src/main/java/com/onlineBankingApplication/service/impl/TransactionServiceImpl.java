package com.onlineBankingApplication.service.impl;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.onlineBankingApplication.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineBankingApplication.dao.PrimaryAccountDao;
import com.onlineBankingApplication.dao.PrimaryTransactionDao;
import com.onlineBankingApplication.dao.RecipientDao;
import com.onlineBankingApplication.dao.SavingAccountDao;
import com.onlineBankingApplication.dao.SavingTransactionDao;
import com.onlineBankingApplication.entity.UserDetails;
import com.onlineBankingApplication.service.TransactionService;
import com.onlineBankingApplication.service.UserService;
import com.onlineBankingApplication.service.storedprocedure.sp.DataAccessRepository;
import com.onlineBankingApplication.service.storedprocedure.sp.Result;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private UserService userService;

	@Autowired
	private PrimaryTransactionDao primaryTransactionDao;

	@Autowired
	private SavingTransactionDao savingTransactionDao;

	@Autowired
	private PrimaryAccountDao primaryAccountDao;
	@Autowired
	private SavingAccountDao savingAccountDao;
	@Autowired
	private RecipientDao recipientDao;
	@Autowired
	private DataAccessRepository dataAccessRepository;

	private static final String SAVINGS = "Savings";

	private static final String PRIMARY = "Primary";

	@Override
	public List<PrimaryTransaction> findPrimaryTransactionList(String username) {
		UserDetails userDetails = userService.findByUserName(username);
		Long accountNumber = userDetails.getPrimaryAccount().getId();
		List<Result> storedProcCall = dataAccessRepository.storedProcCall(accountNumber);
		return userDetails.getPrimaryAccount().getPrimaryTransactions();
	}

	@Override
	public List<SavingsTransaction> findSavingsTransactionList(String username) {
		UserDetails userDetails = userService.findByUserName(username);
		return userDetails.getSavingsAccount().getSavingTransactions();
	}

	@Override
	public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction) {
		primaryTransactionDao.save(primaryTransaction);
	}

	@Override
	public void saveSavingsDepositTransaction(SavingsTransaction savingTransaction) {
		savingTransactionDao.save(savingTransaction);
	}

	@Override
	public void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction) {
		primaryTransactionDao.save(primaryTransaction);
	}

	@Override
	public void saveSavingsWithdrawTransaction(SavingsTransaction savingTransaction) {
		savingTransactionDao.save(savingTransaction);
	}

	@Override
	public void betweenAccountsTransafer(String transferFrom, String transferTo, String amount,
			PrimaryAccount primaryAccount, SavingsAccount savingAccount, Principal principal) {
		UserDetails userDetails = userService.findByUserName(principal.getName());
		if (PRIMARY.equalsIgnoreCase(transferFrom) && SAVINGS.equalsIgnoreCase(transferTo)) {
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			savingAccount.setAccountBalance(savingAccount.getAccountBalance().add(new BigDecimal(amount)));
			primaryAccountDao.save(primaryAccount);
			savingAccountDao.save(savingAccount);

			PrimaryTransaction primaryTransaction = new PrimaryTransaction(new Date(),
					"Transfer Between Primary To Saving", "Account", "Finished", Double.parseDouble(amount),
					primaryAccount.getAccountBalance(), primaryAccount);
			savePrimaryDepositTransaction(primaryTransaction);

		} else if (SAVINGS.equalsIgnoreCase(transferFrom) && PRIMARY.equalsIgnoreCase(transferTo)) {
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
			savingAccount.setAccountBalance(savingAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			primaryAccountDao.save(primaryAccount);
			savingAccountDao.save(savingAccount);

			SavingsTransaction savingTransaction = new SavingsTransaction(new Date(),
					"Transfer Between Saving to Primary", "Account", "Finished", Double.parseDouble(amount),
					savingAccount.getAccountBalance(), savingAccount);
			saveSavingsDepositTransaction(savingTransaction);

		}

	}

	@Override
	public List<Recipient> findRecipientList(Principal principal) {
		String username = principal.getName();
		List<Recipient> recipients = StreamSupport.stream(recipientDao.findAll().spliterator(), true)
				.filter(recipient -> username.equalsIgnoreCase(recipient.getUserDetails().getUsername()))
				.collect(Collectors.toList());
		return recipients;
	}

	@Override
	public Recipient saveRecipient(Recipient recipient) {
		return recipientDao.save(recipient);

	}

	@Override
	public Recipient findRecipientByName(String recipientName) {
		return recipientDao.findByName(recipientName);
	}

	@Override
	public void deleteRecipientByName(String recipientName) {
		recipientDao.deleteByName(recipientName);
	}

	@Override
	public void toSomeoneElseTransfer(Recipient recipient, String accountType, String amount,
			PrimaryAccount primaryAccount, SavingsAccount savingAccount) {
		if (PRIMARY.equalsIgnoreCase(accountType)) {
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			primaryAccountDao.save(primaryAccount);

			PrimaryTransaction primaryTransaction = new PrimaryTransaction(new Date(),
					"Transfer To Recipient " + recipient.getName(), "Transfer", "Finished", Double.parseDouble(amount),
					primaryAccount.getAccountBalance(), primaryAccount);
			savePrimaryDepositTransaction(primaryTransaction);
		} else if (SAVINGS.equalsIgnoreCase(accountType)) {
			savingAccount.setAccountBalance(savingAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			savingAccountDao.save(savingAccount);

			SavingsTransaction savingTransaction = new SavingsTransaction(new Date(),
					"Transfer To Recipient " + recipient.getName(), "Transfer", "Finished", Double.parseDouble(amount),
					savingAccount.getAccountBalance(), savingAccount);
			saveSavingsDepositTransaction(savingTransaction);
		}

	}

}
