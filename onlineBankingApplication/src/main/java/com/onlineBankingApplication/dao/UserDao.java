package com.onlineBankingApplication.dao;

import java.util.List;

import com.onlineBankingApplication.entity.UserDetails;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<UserDetails, Long> {
	UserDetails findByUsername(String username);
	UserDetails findByEmail(String email);
	List<UserDetails> findAll();
}
