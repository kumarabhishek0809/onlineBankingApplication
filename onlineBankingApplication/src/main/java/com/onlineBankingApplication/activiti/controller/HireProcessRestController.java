package com.onlineBankingApplication.activiti.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.onlineBankingApplication.constants.ApplicationConstants;
import com.onlineBankingApplication.domain.Applicant;
import com.onlineBankingApplication.domain.User;
import com.onlineBankingApplication.service.ApplicantService;
import com.onlineBankingApplication.service.UserService;

@RestController
@RequestMapping("/applicant")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class HireProcessRestController {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	private UserService userService;

	@Autowired
	private ApplicantService applicantService;

	@RequestMapping(value = "/user/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> userList() {
		return userService.findUserList();
	}

	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/startHireProcess", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Applicant startHireProcess(@RequestBody Map<String, String> data) {

		Applicant applicant = new Applicant(data.get("name"), data.get("email"), data.get("phoneNumber"));
		// STORE OBJECT INTO DB
		Applicant applicantDB = applicantService.saveOrUpdateApplicant(applicant);

		// A MAP WITH SOME DETAILS IF YOU NEED.
		Map<String, Object> vars = Collections.<String, Object>singletonMap("applicant", applicant);

		// I PREFER DB KEY SO THAT I CAN FETCH IT BACK.
		String processInstanceID_businessKey = "" + applicantDB.getId();

		runtimeService.startProcessInstanceByKey(ApplicationConstants.HIRE_PROCESS_WITH_JPA,
				processInstanceID_businessKey, vars);
		return applicantDB;
	}
	//http://localhost:5050/applicant/telephonic/13/select
	@RequestMapping(value = "/telephonic/{id}/select", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Applicant selectApplicant(@PathVariable("id") String id) {
		Applicant applicantInDB = null;
		if (StringUtils.isNotBlank(id)) {
			ProcessInstance processInstance = runtimeService
					.startProcessInstanceByKey(ApplicationConstants.HIRE_PROCESS_WITH_JPA, id);
			// First, the 'phone interview' should be active
			Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId())
					.taskCandidateGroup("dev-managers").singleResult();

			
			Applicant applicant = applicantService.findApplicantById(new Long(id));
			
			// Completing the phone interview with success should trigger two
			// new tasks
			
			Map<String, Object> taskVariables = new HashMap<>();
			taskVariables.put("telephoneInterviewOutcome", true);
			taskVariables.put("applicant", applicant);
			taskService.complete(task.getId(), taskVariables);
			
			applicant.setCurrentState(ApplicationConstants.SELECT_APPLICANT);
			applicantInDB = applicantService.saveOrUpdateApplicant(applicant);
			
		}
		return applicantInDB;
	}

	@RequestMapping(value = "/telephonic/{id}/reject", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Applicant rejectApplicant(@PathVariable("id") String id) {
		Applicant applicantInDB = null;
		if (StringUtils.isNotBlank(id)) {
			ProcessInstance processInstance = runtimeService
					.startProcessInstanceByKey(ApplicationConstants.HIRE_PROCESS_WITH_JPA, id);
			// First, the 'phone interview' should be active
			Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId())
					.taskCandidateGroup("dev-managers").singleResult();

			
			Applicant applicant = applicantService.findApplicantById(new Long(id));
			
			// Completing the phone interview with success should trigger two
			// new tasks
			
			Map<String, Object> taskVariables = new HashMap<>();
			taskVariables.put("telephoneInterviewOutcome", false);
			taskVariables.put("applicant", applicant);
			taskService.complete(task.getId(), taskVariables);
			
			applicant.setCurrentState(ApplicationConstants.REJECT_APPLICANT);
			applicantInDB = applicantService.saveOrUpdateApplicant(applicant);
		}
		return applicantInDB;
	}

	//http://localhost:5050/applicant/telephonic/all
	@RequestMapping(value = "telephonic/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Applicant> getTelephonicCandidates() {
		List<Applicant> findByCurrentState = applicantService.findByCurrentState(ApplicationConstants.RESUME_SUBMITTED);
		System.out.println(findByCurrentState);
		return findByCurrentState;
	}

}