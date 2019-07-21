package com.onlineBankingApplication.rest.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.onlineBankingApplication.entity.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlineBankingApplication.entity.PrimaryTransaction;
import com.onlineBankingApplication.entity.SavingsTransaction;
import com.onlineBankingApplication.mq.rabbit.Producer;
import com.onlineBankingApplication.service.TransactionService;
import com.onlineBankingApplication.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class UserResourceAPI {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private Producer messageServices;
	
	//@Value("${spring.rabbit.amqp.exchange}")
	private String onlineRabbitMessageExchange;
	
	//@Value("${spring.rabbit.amqp.queue}")
	private String onlineRabbitMessageQueue;
	
	@RequestMapping(value = "/user/all", method = RequestMethod.GET , produces = "application/json")
	public List<UserDetails> userList(){
		return userService.findUserList();
	}
	
	@RequestMapping(value = "/user/{username}/enable")
	public void enableUser(@PathVariable("username") String username){
		userService.enableUser(username);
	}
	
	@RequestMapping(value = "/user/{username}/disable")
	public void disableUser(@PathVariable("username") String username){
		userService.disableUser(username);
	}
	
	@RequestMapping(value="/user/primary/transaction",method = RequestMethod.GET)
	public List<PrimaryTransaction> getPrimaryTransactionList(@RequestParam("username") String username){
		List<PrimaryTransaction> findPrimaryTransactionList = transactionService.findPrimaryTransactionList(username);
		Map<String, String> message = new HashMap<String,String>();
		message.put("MessageTransaction", findPrimaryTransactionList.toString());
		messageServices.sendMessage(onlineRabbitMessageExchange,onlineRabbitMessageQueue,message);
		return findPrimaryTransactionList;
	}
	
	@RequestMapping(value="/user/savings/transaction",method = RequestMethod.GET)
	public List<SavingsTransaction> getSavingsTransactionList(@RequestParam("username") String username){
		return transactionService.findSavingsTransactionList(username);
	}
	
}
