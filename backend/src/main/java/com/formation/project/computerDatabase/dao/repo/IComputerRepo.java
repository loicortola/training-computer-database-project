package com.formation.project.computerDatabase.dao.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formation.project.computerDatabase.base.Computer;

@Repository
public interface IComputerRepo extends CrudRepository<Computer, Long>{
	Page<Computer> findAll(Pageable pageable);
	Page<Computer> findAllByNameContainsIgnoreCase(String name, Pageable pageable);
	
}
