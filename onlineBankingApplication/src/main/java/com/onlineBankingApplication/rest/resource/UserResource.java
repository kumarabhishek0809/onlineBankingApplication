package com.onlineBankingApplication.rest.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlineBankingApplication.domain.PrimaryTransaction;
import com.onlineBankingApplication.domain.SavingsTransaction;
import com.onlineBankingApplication.domain.User;
import com.onlineBankingApplication.service.TransactionService;
import com.onlineBankingApplication.service.UserService;

@RestController
@RequestMapping("/api")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping(value = "/user/all", method = RequestMethod.GET)
	public List<User> userList(){
		return userService.findUserList();
	}
	
	@RequestMapping(value = "/user/{userName}/enable")
	public void enableUser(@PathVariable("userName") String userName){
		userService.enableUser(userName);
	}
	
	@RequestMapping(value = "/user/{userName}/disable")
	public void disableUser(@PathVariable("userName") String userName){
		userService.disableUser(userName);
	}
	
	@RequestMapping(value="/user/primary/transaction",method = RequestMethod.GET)
	public List<PrimaryTransaction> getPrimaryTransactionList(@RequestParam("userName") String userName){
		return transactionService.findPrimaryTransactionList(userName);
	}
	
	@RequestMapping(value="/user/saving/transaction",method = RequestMethod.GET)
	public List<SavingsTransaction> getSavingsTransactionList(@RequestParam("userName") String userName){
		return transactionService.findSavingsTransactionList(userName);
	}
	
}
