package com.onlineBankingApplication.service;

import java.util.List;

import com.onlineBankingApplication.domain.Applicant;

public interface ApplicantService {
	Applicant saveOrUpdateApplicant(Applicant applicant);

	Applicant findApplicantById(Long id);
	
	List<Applicant>  findByCurrentState(String currentState);
	
}
