package com.onlineBankingApplication.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.onlineBankingApplication.dao.UserDao;
import com.onlineBankingApplication.domain.User;

@Service
public class UserSecurityService implements UserDetailsService {

	private static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if(null == user){
			LOG.warn("Username {} not found ",userName);
			throw new UsernameNotFoundException("Username "+userName+" not Found");
		}
		return user;
	}

}
