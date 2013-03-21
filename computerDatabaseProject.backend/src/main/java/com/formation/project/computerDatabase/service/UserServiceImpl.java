package com.formation.project.computerDatabase.service;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.formation.project.computerDatabase.base.User;
import com.formation.project.computerDatabase.dao.IUserDao;

@Service("userService")
public class UserServiceImpl implements IUserService {

	private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	
	@Autowired
    private IUserDao userDao;
	
	@Override
	@Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);

        Assert.notNull(user,"User authentication failed");
        
        logger.info("User successfully retrieved: User name : {}", user.getUsername());
        Hibernate.initialize(user.getAuthorities());
        return user;
    }

	@Override
	public void save(User u) {
		userDao.save(u);		
	}

}
