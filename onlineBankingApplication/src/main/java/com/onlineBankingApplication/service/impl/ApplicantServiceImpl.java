package com.onlineBankingApplication.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineBankingApplication.dao.ApplicantDao;
import com.onlineBankingApplication.entity.Applicant;
import com.onlineBankingApplication.service.ApplicantService;

@Service
public class ApplicantServiceImpl implements ApplicantService {

	@Autowired
	private ApplicantDao applicantDao;

	@Override
	public Applicant saveOrUpdateApplicant(Applicant applicant) {
		return applicantDao.save(applicant);
	}

	@Override
	public Applicant findApplicantById(Long id) {
		Optional<Applicant> findById = applicantDao.findById(id);
		return findById.isPresent() ? findById.get() : null;
	}

	@Override
	public List<Applicant> findByCurrentState(String currentState) {
		return applicantDao.findByCurrentState(currentState);
	}

}
