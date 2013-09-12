package com.formation.computerdatabase.back.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.formation.computerdatabase.back.dao.custom.ComputerDaoCustom;
import com.formation.computerdatabase.core.domain.Computer;
import com.formation.computerdatabase.core.domain.QCompany;
import com.formation.computerdatabase.core.domain.QComputer;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Order;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.path.PathBuilder;

public class ComputerDaoImpl implements ComputerDaoCustom {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page<Computer> getComputers(Pageable page, String computerName, String companyName) {		
		
		BooleanBuilder bb = new BooleanBuilder();
	    if (!computerName.trim().isEmpty())
	        bb.and(QComputer.computer.name.containsIgnoreCase(computerName));
	    if (!companyName.trim().isEmpty())
	        bb.and(QComputer.computer.company.name.containsIgnoreCase(companyName));
	   
	   JPAQuery query = new JPAQuery(entityManager)
       						.from(QComputer.computer)
	   						.leftJoin(QComputer.computer.company, QCompany.company)
       						.where(bb);
	   
	   long total = query.count();
	   
       query.offset(page.getOffset())
       		.limit(page.getPageSize())
       		.fetch();
	   
	   for (Sort.Order o : page.getSort()) {
           query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, new PathBuilder(Computer.class, o.getProperty())));
       }
	   
	   List<Computer> computerList = query.list(QComputer.computer);
	   
	   Page<Computer> computersPage = new PageImpl<Computer>(computerList, page, total);
	   return computersPage;
	}
	
	@Override
	public List<Computer> getComputers(String computerName, String companyName) {		
		
		BooleanBuilder bb = new BooleanBuilder();
	    if (!computerName.trim().isEmpty())
	        bb.and(QComputer.computer.name.containsIgnoreCase(computerName));
	    if (!companyName.trim().isEmpty())
	        bb.and(QComputer.computer.company.name.containsIgnoreCase(companyName));
	   
	   JPAQuery query = new JPAQuery(entityManager)
       						.from(QComputer.computer)
	   						.leftJoin(QComputer.computer.company, QCompany.company)
       						.where(bb);
	   
	   query.fetch();
	   
	   List<Computer> computersList = query.list(QComputer.computer);
	  	   
	   return computersList;
	}
	
}
