package com.formation.project.computerDatabase.dao;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.formation.project.computerDatabase.base.Company;

public interface ICompanyDao {
	public List<Company> findAll(Sort sort);
	public Company findOne(Long companyId);
}
