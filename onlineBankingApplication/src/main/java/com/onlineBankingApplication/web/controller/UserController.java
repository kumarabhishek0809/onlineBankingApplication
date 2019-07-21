package com.onlineBankingApplication.web.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onlineBankingApplication.entity.UserDetails;
import com.onlineBankingApplication.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Principal principal, Model model) {
		UserDetails userDetails = userService.findByUserName(principal.getName());
		model.addAttribute("user", userDetails);
		return "profile";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String profilePost(@ModelAttribute("user") UserDetails userDetailsLocal, Model model) {
		UserDetails userDetails = userService.findByUserName(userDetailsLocal.getUsername());
		userDetails.setUsername(userDetailsLocal.getUsername());
		userDetails.setFirstName(userDetailsLocal.getFirstName());
		userDetails.setLastName(userDetailsLocal.getLastName());
		userDetails.setEmail(userDetailsLocal.getEmail());
		userDetails.setPhone(userDetailsLocal.getPhone());

		model.addAttribute("user", userDetails);
		userService.save(userDetails);
		return "profile";

	}

}
