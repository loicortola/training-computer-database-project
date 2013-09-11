package com.formation.computerdatabase.back.service.impl;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.formation.computerdatabase.back.dao.UserDao;
import com.formation.computerdatabase.back.service.UserService;
import com.formation.computerdatabase.core.domain.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	private final static Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);

		Assert.notNull(user, "User authentication failed");

		logger.info("User successfully retrieved: User name : {}",
				user.getUsername());
		Hibernate.initialize(user.getAuthorities());

		return user;
	}

	@Override
	public void save(User u) {
		userDao.save(u);
	}

}
