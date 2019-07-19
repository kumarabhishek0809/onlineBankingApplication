package com.onlineBankingApplication.web.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onlineBankingApplication.entity.Appointment;
import com.onlineBankingApplication.entity.User;
import com.onlineBankingApplication.service.AppointmentService;
import com.onlineBankingApplication.service.UserService;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private UserService userService;

	@Autowired
	private AppointmentService appointmentService;

	@RequestMapping(value ="/create" , method = RequestMethod.GET)
	public String createAppointment(Model model){
		Appointment appointment = new Appointment();
		model.addAttribute("appointment",appointment);
		model.addAttribute("dateString","");
		return "appointment";
	}

	@RequestMapping(value = "/create" , method = RequestMethod.POST)
	public String createAppointmentPost(@ModelAttribute("appointment") Appointment appointment,
			@ModelAttribute("dateString")String dateString , Principal principal) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date = format.parse(dateString);
		appointment.setAppointmentDate(date);
		
		User user = userService.findByUserName(principal.getName());
		appointment.setUser(user);
		
		appointmentService.createAppointment(appointment);
		
		return "redirect:/userFront";
		
	}
	
}
