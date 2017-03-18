package com.onlineBankingApplication.service.impl;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineBankingApplication.dao.PrimaryAccountDao;
import com.onlineBankingApplication.dao.PrimaryTransactionDao;
import com.onlineBankingApplication.dao.RecipientDao;
import com.onlineBankingApplication.dao.SavingAccountDao;
import com.onlineBankingApplication.dao.SavingTransactionDao;
import com.onlineBankingApplication.domain.PrimaryAccount;
import com.onlineBankingApplication.domain.PrimaryTransaction;
import com.onlineBankingApplication.domain.Recipient;
import com.onlineBankingApplication.domain.SavingAccount;
import com.onlineBankingApplication.domain.SavingTransaction;
import com.onlineBankingApplication.domain.User;
import com.onlineBankingApplication.service.TransactionService;
import com.onlineBankingApplication.service.UserService;

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

	private static final String SAVINGS = "Savings";

	private static final String PRIMARY = "Primary";

	@Override
	public List<PrimaryTransaction> findPrimaryTransactionList(String username) {
		User user = userService.findByUserName(username);
		return user.getPrimaryAccount().getPrimaryTransactions();
	}

	@Override
	public List<SavingTransaction> findSavingsTransactionList(String username) {
		User user = userService.findByUserName(username);
		return user.getSavingAccount().getSavingTransactions();
	}

	@Override
	public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction) {
		primaryTransactionDao.save(primaryTransaction);
	}

	@Override
	public void saveSavingsDepositTransaction(SavingTransaction savingTransaction) {
		savingTransactionDao.save(savingTransaction);
	}

	@Override
	public void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction) {
		primaryTransactionDao.save(primaryTransaction);
	}

	@Override
	public void saveSavingsWithdrawTransaction(SavingTransaction savingTransaction) {
		savingTransactionDao.save(savingTransaction);
	}

	@Override
	public void betweenAccountsTransafer(String transferFrom, String transferTo, String amount,
			PrimaryAccount primaryAccount, SavingAccount savingAccount, Principal principal) {
		User user = userService.findByUserName(principal.getName());
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

			SavingTransaction savingTransaction = new SavingTransaction(new Date(),
					"Transfer Between Saving to Primary", "Account", "Finished", Double.parseDouble(amount),
					savingAccount.getAccountBalance(), savingAccount);
			saveSavingsDepositTransaction(savingTransaction);

		}

	}

	@Override
	public List<Recipient> findRecipientList(Principal principal) {
		String userName = principal.getName();
		List<Recipient> recipients = StreamSupport.stream(recipientDao.findAll().spliterator(), true)
				.filter(recipient -> userName.equalsIgnoreCase(recipient.getUser().getUserName()))
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
			PrimaryAccount primaryAccount, SavingAccount savingAccount) {
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

			SavingTransaction savingTransaction = new SavingTransaction(new Date(),
					"Transfer To Recipient " + recipient.getName(), "Transfer", "Finished", Double.parseDouble(amount),
					savingAccount.getAccountBalance(), savingAccount);
			saveSavingsDepositTransaction(savingTransaction);
		}

	}

}
