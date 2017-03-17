package com.onlineBankingApplication.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onlineBankingApplication.domain.PrimaryAccount;
import com.onlineBankingApplication.domain.SavingAccount;
import com.onlineBankingApplication.domain.User;
import com.onlineBankingApplication.service.TransactionService;
import com.onlineBankingApplication.service.UserService;

@Controller
@RequestMapping("/transfer")
public class TransferController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/betweenAccounts", method = RequestMethod.GET)
	public String betweenAccounts(Model model) {
		model.addAttribute("transferFrom", "");
		model.addAttribute("transferTo", "");
		model.addAttribute("amount", "");

		return "betweenAccounts";
	}

	@RequestMapping(value = "/betweenAccounts", method = RequestMethod.POST)
	public String betweenAccountsPost(@ModelAttribute("transferFrom") String transferFrom,
			@ModelAttribute("transferTo") String transferTo, @ModelAttribute("amount") String amount,
			Principal principal) throws Exception {
		User user = userService.findByUserName(principal.getName());
		PrimaryAccount primaryAccount = user.getPrimaryAccount();
		SavingAccount savingAccount = user.getSavingAccount();
		transactionService.betweenAccountsTransafer(transferFrom, transferTo, amount, primaryAccount, savingAccount,principal);

		return "redirect:/userFront";

	}

}
