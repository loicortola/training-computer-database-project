package com.formation.project.computerDatabase.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.formation.project.computerDatabase.base.Company;

public interface ICompanyDao extends CrudRepository<Company, Long>, QueryDslPredicateExecutor<Company> {
	List<Company> findAll(Sort sort);
}
