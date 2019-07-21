package com.onlineBankingApplication.web.controller;

import java.security.Principal;
import java.util.List;

import com.onlineBankingApplication.entity.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlineBankingApplication.entity.PrimaryAccount;
import com.onlineBankingApplication.entity.Recipient;
import com.onlineBankingApplication.entity.SavingsAccount;
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
		UserDetails userDetails = userService.findByUserName(principal.getName());
		PrimaryAccount primaryAccount = userDetails.getPrimaryAccount();
		SavingsAccount savingAccount = userDetails.getSavingsAccount();
		transactionService.betweenAccountsTransafer(transferFrom, transferTo, amount, primaryAccount, savingAccount,principal);

		return "redirect:/userFront";

	}

	
	@RequestMapping(value = "/recipient" , method = RequestMethod.GET)
	public String recipient(Model model,Principal principal){
		List<Recipient>  recipients = transactionService.findRecipientList(principal);
		Recipient recipient = new Recipient();
		
		model.addAttribute("recipientList",recipients);
		model.addAttribute("recipient",recipient);
		
		return "recipient";
	}
	
	
	@RequestMapping(value = "/recipient/save" , method = RequestMethod.POST)
	public String recipientPost(@ModelAttribute("recipient") Recipient recipient,Principal principal){
		UserDetails userDetails = userService.findByUserName(principal.getName());
		recipient.setUserDetails(userDetails);
		transactionService.saveRecipient(recipient);
		return "redirect:/transfer/recipient";
	}
	
	@RequestMapping(value = "/recipient/edit",method = RequestMethod.GET)
	public String recipientEdit(@RequestParam(value = "recipientName") String recipientName,Model model,Principal principal){
		Recipient recipient =  transactionService.findRecipientByName(recipientName);
		List<Recipient>  recipients = transactionService.findRecipientList(principal);
		model.addAttribute("recipientList",recipients);
		model.addAttribute("recipient",recipient);
		
		return "recipient";
	}
	
	
	@RequestMapping(value = "/recipient/delete",method = RequestMethod.GET)
	public String recipientDelete(@RequestParam(value = "recipientName") String recipientName,Model model,Principal principal){
		transactionService.deleteRecipientByName(principal.getName());
		List<Recipient>  recipients = transactionService.findRecipientList(principal);
		Recipient recipient = new Recipient();
		model.addAttribute("recipientList",recipients);
		model.addAttribute("recipient",recipient);
		
		return "recipient";
	}
	
	@RequestMapping(value = "/toSomeoneElse" , method = RequestMethod.GET)
	public String toSomeoneElse(Model model,Principal principal){
		List<Recipient> recipients = transactionService.findRecipientList(principal);
		model.addAttribute("recipientList",recipients);
		model.addAttribute("accountType","");
		
		return "toSomeoneElse";
	}
	
	@RequestMapping(value = "/toSomeoneElse" , method = RequestMethod.POST)
	public String toSomeoneElsePost(@ModelAttribute("recipientName") String recipientName,
			@ModelAttribute("accountType") String accountType,@ModelAttribute("amount") String amount,Principal principal){
		UserDetails userDetails = userService.findByUserName(principal.getName());
		Recipient recipient = transactionService.findRecipientByName(recipientName);
		transactionService.toSomeoneElseTransfer(recipient,accountType,amount, userDetails.getPrimaryAccount(), userDetails.getSavingsAccount());
		
		return "redirect:/userFront";
		
	}
	
}
