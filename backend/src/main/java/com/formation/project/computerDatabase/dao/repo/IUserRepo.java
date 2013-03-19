package com.formation.project.computerDatabase.dao.repo;

import org.springframework.data.repository.CrudRepository;

import com.formation.project.computerDatabase.base.User;

public interface IUserRepo extends CrudRepository<User, Long> {
	public User findByUsername(String username);
}
