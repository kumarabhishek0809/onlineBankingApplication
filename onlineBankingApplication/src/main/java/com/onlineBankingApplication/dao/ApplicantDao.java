package com.onlineBankingApplication.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.onlineBankingApplication.entity.Applicant;

public interface ApplicantDao extends CrudRepository<Applicant, Long>{

	List<Applicant>  findByCurrentState(String currentState);

}
