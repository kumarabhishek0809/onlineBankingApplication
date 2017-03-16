package com.onlineBankingApplication.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onlineBankingApplication.domain.User;
import com.onlineBankingApplication.service.AccountService;
import com.onlineBankingApplication.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;

	@RequestMapping("/primaryAccount")
	public String primaryAccount(Model model,Principal principal) {
		User user = userService.findByUserName(principal.getName());
		model.addAttribute("primaryAccount",user.getPrimaryAccount());
		return "primaryAccount";
	}

	@RequestMapping("/savingsAccount")
	public String savingsAccount(Model model,Principal principal) {
		User user = userService.findByUserName(principal.getName());
		model.addAttribute("savingsAccount",user.getSavingAccount());
		return "savingsAccount";
	}
	
	@RequestMapping(value = "/deposit", method = RequestMethod.GET)
	public String deposit(Model model){
		model.addAttribute("accountType","");
		model.addAttribute("amount","");
		return "deposit";
	}
	
	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public String depositPost(@ModelAttribute("amount") String amount,@ModelAttribute("accountType") String accountType ,
			Principal principal){
		accountService.deposit(accountType,Double.parseDouble(amount),principal);
		return "redirect:/userFront";
	}
	
	@RequestMapping(value = "/withdraw", method = RequestMethod.GET)
	public String withdraw(Model model){
		model.addAttribute("accountType","");
		model.addAttribute("amount","");
		return "deposit";
	}
	
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public String withdrawPost(@ModelAttribute("amount") String amount,@ModelAttribute("accountType") String accountType ,
			Principal principal){
		accountService.withdraw(accountType,Double.parseDouble(amount),principal);
		return "redirect:/userFront";
	}
	
	
}
