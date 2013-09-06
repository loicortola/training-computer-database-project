package com.formation.computerdatabase.back.dao;

import org.springframework.data.repository.CrudRepository;

import com.formation.computerdatabase.core.domain.User;

public interface UserDao extends CrudRepository<User, Long> {
	public User findByUsername(String username);
}
