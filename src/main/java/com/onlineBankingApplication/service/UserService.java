package com.onlineBankingApplication.service;

import com.onlineBankingApplication.domain.User;

public interface UserService {

	User findByUserName(String userName);

	User findByEmail(String email);

	boolean checkUserExists(String userName, String email);

	boolean checkUserNameExists(String userName);

	boolean checkEmailExists(String email);

	void save(User user);

	void create(User user);
}
