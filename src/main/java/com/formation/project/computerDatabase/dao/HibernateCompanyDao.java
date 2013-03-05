package com.formation.project.computerDatabase.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.formation.project.computerDatabase.base.Company;

@Repository
public class HibernateCompanyDao implements ICompanyDao {
	
	@Autowired
	private SessionFactory sf;
	
	public HibernateCompanyDao() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getCompanies() {
		
		return  sf.getCurrentSession()
				.createCriteria(Company.class)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Company getCompany(Long companyId) {
		
		List<Company> companiesList 	= new ArrayList<Company>();
		
		
		companiesList = sf.getCurrentSession()
						.createCriteria(Company.class)
						.add(Restrictions.eq("id", companyId))
						.list();
		
		if(companiesList.size() == 1)
			return companiesList.get(0);
		
		return null;
	}
}
