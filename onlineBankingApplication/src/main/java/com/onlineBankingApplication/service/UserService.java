package com.onlineBankingApplication.service;

import java.util.Set;

import com.onlineBankingApplication.domain.User;
import com.onlineBankingApplication.domain.UserRole;

public interface UserService {

	User findByUserName(String username);

	User findByEmail(String email);

	boolean checkUserExists(String username, String email);

	boolean checkUserNameExists(String username);

	boolean checkEmailExists(String email);

	void save(User user);

	User createUser(User user, Set<UserRole> userRoles);
}
