package com.formation.project.computerDatabase.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.base.TableSort;

@Repository
public class HibernateComputerDao implements IComputerDao {
	
	@Autowired
	private SessionFactory sf;
	
	public HibernateComputerDao() {
		
	}
	
	private Criteria getDefaultCriteria(String name) {
		return 	sf.getCurrentSession()
				.createCriteria(Computer.class,"computer")
				.add(Restrictions.like("computer.name", String.format("%%%s%%", name)).ignoreCase());
	}
	
	private Criteria getJointCriteria(String name) {
		return sf.getCurrentSession()
		.createCriteria(Computer.class,"computer")
		.createAlias("company","company", JoinType.INNER_JOIN)
		.add(Restrictions.like("computer.name", String.format("%%%s%%", name)).ignoreCase());
	}
	
	private Order getOrder(TableSort sortBy) {
		
		if(sortBy.isAsc()) {
			return Order.asc(sortBy.getSortString()).ignoreCase();
		}
		else {
			return Order.desc(sortBy.getSortString()).ignoreCase();
		}
	}
	
	@Override
	public Long addComputer(Computer computer) {
		
		sf.getCurrentSession().save(computer);
		sf.getCurrentSession().refresh(computer);
		
		return computer.getId();
	}

	@Override
	public void updateComputer(Computer computer) {
		sf.getCurrentSession().update(computer);
	}

	@Override
	public void deleteComputer(Long computerId) {
		Computer computer = (Computer) sf.getCurrentSession().get(Computer.class, computerId);
		sf.getCurrentSession().delete(computer);			
	}

	@Override
	public Computer getComputer(Long computerId) {
		return (Computer) sf.getCurrentSession().get(Computer.class,computerId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> getComputers(Integer currentPage, Integer resultsPerPage, TableSort sortBy, String name) {

		return 	getJointCriteria(name)
				.addOrder(getOrder(sortBy))
				.setFirstResult((currentPage-1)*resultsPerPage)
				.setMaxResults(resultsPerPage)
				.list();
	}

	@Override
	public Integer getComputerCount(String name) {
		
		return  ((Long) getDefaultCriteria(name)
				.add(Restrictions.eq("isVisible", true))
				.setProjection(Projections.rowCount()).uniqueResult())
				.intValue();
				
	}

}
