package com.formation.project.computerDatabase.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.dao.repo.ICompanyRepo;

@Repository
public class CompanyDaoImpl implements ICompanyDao {

	@Autowired
	ICompanyRepo companyRepository;

	@Override
	public List<Company> findAll(Sort sort) {
		return companyRepository.findAll(sort);
	}

	@Override
	public Company findOne(Long companyId) {
		return companyRepository.findOne(companyId);
	}

}
