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
import com.onlineBankingApplication.entity.UserDetails;
import com.onlineBankingApplication.entity.UserRole;
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
	public void save(UserDetails userDetails) {
		userDao.save(userDetails);
	}

	@Override
	public UserDetails findByUserName(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public UserDetails findByEmail(String email) {
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
	public UserDetails createUser(UserDetails userDetails, Set<UserRole> userRoles) {
		UserDetails localUserDetails = userDao.findByUsername(userDetails.getUsername());
		if (localUserDetails != null) {
			LOG.info("UserDetailsData with username {} already Exists", userDetails.getUsername());
		} else {
			String encryptedPassword = passwordEncoder.encode(userDetails.getPassword());
			for (UserRole userRole : userRoles) {
				roleDao.save(userRole.getRole());
				userDetails.getUserRoles().addAll(userRoles);
				userDetails.setPassword(encryptedPassword);
				userDetails.setPrimaryAccount(accountService.createPrimaryAccount());
				userDetails.setSavingAccount(accountService.createSavingAccount());
				userDetails.setUsername(userDetails.getUsername());
				localUserDetails = userDao.save(userDetails);
			}
		}
		return localUserDetails;
	}

	@Override
	public List<UserDetails> findUserList() {
		return StreamSupport.stream(userDao.findAll().spliterator(), true).collect(Collectors.toList());

	}

	@Override
	public void enableUser(String username) {
		UserDetails userDetails = findByUserName(username);
		userDetails.setEnabled(true);
		userDao.save(userDetails);
	}

	@Override
	public void disableUser(String username) {
		UserDetails userDetails = findByUserName(username);
		userDetails.setEnabled(false);
		userDao.save(userDetails);

	}

}
