package com.formation.computerdatabase.back.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.formation.computerdatabase.core.domain.User;

public interface UserService extends UserDetailsService {
	public void save(User u);
}
