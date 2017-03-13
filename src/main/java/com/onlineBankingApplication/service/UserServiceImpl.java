package com.onlineBankingApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineBankingApplication.dao.UserDao;
import com.onlineBankingApplication.domain.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

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

}
