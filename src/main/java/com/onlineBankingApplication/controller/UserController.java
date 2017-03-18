package com.onlineBankingApplication.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onlineBankingApplication.domain.User;
import com.onlineBankingApplication.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Principal principal, Model model) {
		User user = userService.findByUserName(principal.getName());
		model.addAttribute("user", user);
		return "profile";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String profilePost(@ModelAttribute("user") User userLocal, Model model) {
		User user = userService.findByUserName(userLocal.getUsername());
		user.setUserName(userLocal.getUserName());
		user.setFirstName(userLocal.getFirstName());
		user.setLastName(userLocal.getLastName());
		user.setEmail(userLocal.getEmail());
		user.setPhone(userLocal.getPhone());

		model.addAttribute("user", user);
		userService.save(user);
		return "profile";

	}

}
