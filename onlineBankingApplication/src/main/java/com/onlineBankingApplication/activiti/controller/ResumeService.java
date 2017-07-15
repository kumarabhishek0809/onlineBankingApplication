package com.onlineBankingApplication.activiti.controller;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onlineBankingApplication.constants.ApplicationConstants;
import com.onlineBankingApplication.domain.Applicant;
import com.onlineBankingApplication.service.ApplicantService;

@Component
public class ResumeService {

	@Autowired
	private ApplicantService applicantService;

	// THIS METHOD IS CONFIGURED INTO ACTIVI TO BE CALLED FOR STORING
	public void storeResume(DelegateExecution delegateExecution) {
		if (delegateExecution != null && delegateExecution.getBusinessKey() != null) {
			Applicant findApplicant = applicantService.findApplicantById(Long.valueOf(delegateExecution.getBusinessKey()));
			findApplicant.setCurrentState(ApplicationConstants.RESUME_SUBMITTED);
			applicantService.saveOrUpdateApplicant(findApplicant);
			System.out.println("Storing resume ..." + findApplicant);
		}
	}

}
