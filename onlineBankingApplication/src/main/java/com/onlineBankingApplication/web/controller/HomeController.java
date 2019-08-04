package com.onlineBankingApplication.web.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import com.onlineBankingApplication.entity.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onlineBankingApplication.dao.RoleDao;
import com.onlineBankingApplication.entity.PrimaryAccount;
import com.onlineBankingApplication.entity.SavingsAccount;
import com.onlineBankingApplication.entity.UserRole;
import com.onlineBankingApplication.service.UserService;

@Controller
public class HomeController {
	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private RoleDao roleDao;

	@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String signUp(Model model) {
		UserDetails userDetails = new UserDetails();
		model.addAttribute("userDetails", userDetails);
		return "signUp";
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String signUpPost(@ModelAttribute("userDetails") UserDetails userDetails, Model model) {
		if (userService.checkUserExists(userDetails.getUsername(), userDetails.getEmail())) {
			if (userService.checkEmailExists(userDetails.getEmail())) {
				model.addAttribute("emailExists", true);
			}
			if (userService.checkUserNameExists(userDetails.getUsername())) {
				model.addAttribute("usernameExits", true);
			}
			return "signUp";
		} else {
			Set<UserRole> userRoles = new HashSet<>();
			userRoles.add(new UserRole(userDetails, roleDao.findByName("ROLE_USER")));
			userService.createUser(userDetails, userRoles);
			return "redirect:/";
		}

	}

	@RequestMapping("/userFront")
	public String userFront(Principal principal, Model model) {
		UserDetails userDetails = userService.findByUserName(principal.getName());
		PrimaryAccount primaryAccount = userDetails.getPrimaryAccount();
		SavingsAccount savingAccount = userDetails.getSavingsAccount();

		model.addAttribute("primaryAccount", primaryAccount);
		model.addAttribute("savingsAccount", savingAccount);

		return "userFront";

	}
}
