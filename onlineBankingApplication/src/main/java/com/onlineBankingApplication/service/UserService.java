package com.onlineBankingApplication.service;

import java.util.List;
import java.util.Set;

import com.onlineBankingApplication.entity.UserDetails;
import com.onlineBankingApplication.entity.UserRole;

public interface UserService {

	UserDetails findByUserName(String username);

	UserDetails findByEmail(String email);

	boolean checkUserExists(String username, String email);

	boolean checkUserNameExists(String username);

	boolean checkEmailExists(String email);

	void save(UserDetails userDetails);

	UserDetails createUser(UserDetails userDetails, Set<UserRole> userRoles);

	List<UserDetails> findUserList();

	void enableUser(String username);

	void disableUser(String username);
}
