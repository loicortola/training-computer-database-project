package com.formation.project.computerDatabase.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.formation.project.computerDatabase.base.Computer;

public interface ComputerRepository extends Repository<Computer, Long> {

	Computer findOne(Long primaryKey);
	List<Computer> findAll();
	Page<Computer> findAll(Pageable pageable);
	List<Computer> findAllByNameLikeIgnoreCase(String name);
	Page<Computer> findAllByNameLikeIgnoreCase(String name, Pageable pageable);
    void delete(Computer entity);
    Computer save(Computer entity);

}
