package com.onlineBankingApplication.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onlineBankingApplication.domain.User;

@Controller
public class HomeController {

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
	
	@RequestMapping(value = "/signUp",method=RequestMethod.POST)
	public void signUpPost(@ModelAttribute("user") User user, Model model){
//		if(userService.checkUserExists(user.getUserName(),user.getEmail())){
//			if(userService.checkEmailExists(user.getEmail())){
//				model.addAttribute("emailExists",true);
//			}
//			if(userService.checkUserNameExists(user.getUserName())){
//				model.addAttribute("userNameExits",true);
//			}
//			return "signUp";
//		}else {
//			Set<UserRoles> userRoles = new HashSet<>();
//			userRoles.add(new UserRole(user,roleDao.findUserByName("USER")));
//			userService.createUser(user,userRoles);
//		}
		
	}
}
