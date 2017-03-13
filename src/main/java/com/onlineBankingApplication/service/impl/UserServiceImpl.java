package com.onlineBankingApplication.service.impl;

import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.onlineBankingApplication.dao.RoleDao;
import com.onlineBankingApplication.dao.UserDao;
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
	public User findByUserName(String userName) {
		return userDao.findByUserName(userName);
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public boolean checkUserExists(String userName, String email) {
		if (checkUserNameExists(userName) || checkEmailExists(email)) {
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
	public boolean checkUserNameExists(String userName) {
		if (null != findByUserName(userName)) {
			return true;
		}
		return false;
	}

	@Override
	public void create(User user) {

	}

	@Transactional
	public User createUser(User user,Set<UserRole> userRoles) {
		User localUser = userDao.findByUserName(user.getUserName());
		if(localUser != null){
			LOG.info("User with username {} already Exists",user.getUserName());
		}else {
			String encryptedPassword = passwordEncoder.encode(user.getPassword());
			for(UserRole userRole :userRoles){
				roleDao.save(userRole.getRole());
				user.getUserRoles().addAll(userRoles);
				user.setPrimaryAccount(accountService.createPrimaryAccount());
				user.setSavingAccount(accountService.createSavingAccount());
				localUser = userDao.save(user);
			}
		}
		return localUser;
	}

}
