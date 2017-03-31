package com.onlineBankingApplication.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.onlineBankingApplication.dao.RoleDao;
import com.onlineBankingApplication.dao.UserDao;
import com.onlineBankingApplication.domain.Recipient;
import com.onlineBankingApplication.domain.User;
import com.onlineBankingApplication.domain.UserRole;
import com.onlineBankingApplication.security.config.UserSecurityService;
import com.onlineBankingApplication.service.AccountService;
import com.onlineBankingApplication.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AccountService accountService;

	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public User findByUserName(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public boolean checkUserExists(String username, String email) {
		if (checkUserNameExists(username) || checkEmailExists(email)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkEmailExists(String email) {
		if (null != findByEmail(email)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkUserNameExists(String username) {
		if (null != findByUserName(username)) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public User createUser(User user, Set<UserRole> userRoles) {
		User localUser = userDao.findByUsername(user.getUsername());
		if (localUser != null) {
			LOG.info("User with username {} already Exists", user.getUsername());
		} else {
			String encryptedPassword = passwordEncoder.encode(user.getPassword());
			for (UserRole userRole : userRoles) {
				roleDao.save(userRole.getRole());
				user.getUserRoles().addAll(userRoles);
				user.setPassword(encryptedPassword);
				user.setPrimaryAccount(accountService.createPrimaryAccount());
				user.setSavingAccount(accountService.createSavingAccount());
				user.setUsername(user.getUsername());
				localUser = userDao.save(user);
			}
		}
		return localUser;
	}

	@Override
	public List<User> findUserList() {
		return StreamSupport.stream(userDao.findAll().spliterator(), true).collect(Collectors.toList());

	}

	@Override
	public void enableUser(String username) {
		User user = findByUserName(username);
		user.setEnabled(true);
		userDao.save(user);

	}

	@Override
	public void disableUser(String username) {
		User user = findByUserName(username);
		user.setEnabled(false);
		userDao.save(user);

	}

}
