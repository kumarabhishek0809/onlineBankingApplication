package com.onlineBankingApplication.dao;

import org.springframework.data.repository.CrudRepository;

import com.onlineBankingApplication.domain.User;

public interface UserDao extends CrudRepository<User, Long> {
	User findByUserName(String userName);

	User findByEmail(String email);
}
