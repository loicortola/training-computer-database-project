package com.formation.project.computerDatabase.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.formation.project.computerDatabase.base.Computer;

public interface IComputerDao extends CrudRepository<Computer, Long>, QueryDslPredicateExecutor<Computer> {
	Page<Computer> findAll(Pageable pageable);
	Page<Computer> findAllByNameContainsIgnoreCase(String name, Pageable pageable);
	
}
