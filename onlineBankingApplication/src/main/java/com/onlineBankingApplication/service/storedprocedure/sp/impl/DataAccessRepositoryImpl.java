package com.onlineBankingApplication.service.storedprocedure.sp.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

import com.onlineBankingApplication.service.storedprocedure.sp.DataAccessRepository;
import com.onlineBankingApplication.service.storedprocedure.sp.Result;

@Repository
public class DataAccessRepositoryImpl implements DataAccessRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Result> storedProcCall(Long input) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("PRIMARY_TRANSACTIONS"); //onlinebanking.StoredProcName
		  // Set the parameters of the stored procedure.
		   String firstParam = "firstParam";
		   storedProcedure.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN); //Named parameters are used for a callable statement, but database metadata indicates named parameters are not supported.
		   storedProcedure.setParameter(1, input);

		   // Call the stored procedure. 
		   List<Object[]> storedProcedureResults = storedProcedure.getResultList();
		   
		   System.out.println("storedProcedureResults"+storedProcedureResults);

		   // Use Java 8's cool new functional programming paradigm to map the objects from the stored procedure results
		   return storedProcedureResults.stream().map(result -> new Result(
		         (BigInteger) result[0],
		         (Double) result[1]
		   )).collect(Collectors.toList());
	}

}
