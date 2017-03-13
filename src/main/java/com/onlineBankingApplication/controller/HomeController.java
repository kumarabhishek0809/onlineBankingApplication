package com.onlineBankingApplication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onlineBankingApplication.domain.User;
import com.onlineBankingApplication.service.UserService;

@Controller
public class HomeController {
	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private UserService userService;

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
		User user = new User();
		model.addAttribute("user", user);
		return "signUp";
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String signUpPost(@ModelAttribute("user") User user, Model model) {
		if (userService.checkUserExists(user.getUserName(), user.getEmail())) {
			if (userService.checkEmailExists(user.getEmail())) {
				model.addAttribute("emailExists", true);
			}
			if (userService.checkUserNameExists(user.getUserName())) {
				model.addAttribute("userNameExits", true);
			}
			return "signUp";
		} else {
//			Set<UserRoles> userRoles = new HashSet<>();
//			userRoles.add(new UserRole(user, roleDao.findUserByName("USER")));
//			userService.createUser(user, userRoles);
			userService.create(user);
			return "redirect:/";
		}

	}
}
