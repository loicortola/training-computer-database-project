package com.formation.computerdatabase.back.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.formation.computerdatabase.core.domain.Company;

public interface CompanyDao extends CrudRepository<Company, Long>, QueryDslPredicateExecutor<Company> {
	
	public List<Company> findAll(Sort sort);
	public Company findOne(Long companyId);
}
