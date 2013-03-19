package com.formation.project.computerDatabase.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.formation.project.computerDatabase.base.User;

public interface IUserService extends UserDetailsService{
	public void save(User u);
}
