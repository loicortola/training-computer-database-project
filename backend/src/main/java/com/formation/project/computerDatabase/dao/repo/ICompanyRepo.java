package com.formation.project.computerDatabase.dao.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.formation.project.computerDatabase.base.Company;

@Repository
public interface ICompanyRepo extends CrudRepository<Company, Long>, QueryDslPredicateExecutor<Company> {
	List<Company> findAll(Sort sort);
}
