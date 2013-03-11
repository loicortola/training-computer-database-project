package com.formation.project.computerDatabase.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.formation.project.computerDatabase.base.Computer;

public interface ComputerRepository extends CrudRepository<Computer, Long> {

	Page<Computer> findAll(Pageable pageable);
	List<Computer> findAllByNameLikeIgnoreCase(String name);
	Page<Computer> findAllByNameLikeIgnoreCase(String name, Pageable pageable);

}
