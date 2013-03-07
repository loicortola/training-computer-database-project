package com.formation.project.computerDatabase.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.formation.project.computerDatabase.base.Company;


public interface CompanyRepository extends CrudRepository<Company, Long> {
	List<Company> findAll(Sort sort);
}