package com.formation.project.computerDatabase.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.formation.project.computerDatabase.base.Stat;

@Repository
public class HibernateStatsDao implements IStatsDao {
	
	@Autowired
	private SessionFactory sf;
	
	public HibernateStatsDao() {
		
	}
	
	@Override
	public void logOperation(Long computerId, String statType) {
		sf.getCurrentSession().save(new Stat(computerId, statType));
	}

}
