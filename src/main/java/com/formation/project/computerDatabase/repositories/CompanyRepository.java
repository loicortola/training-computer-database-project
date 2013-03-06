package com.formation.project.computerDatabase.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;

import com.formation.project.computerDatabase.base.Company;


public interface CompanyRepository extends Repository<Company, Long> {
	List<Company> findAll(Sort sort);
	Company findOne(Long primaryKey);
}