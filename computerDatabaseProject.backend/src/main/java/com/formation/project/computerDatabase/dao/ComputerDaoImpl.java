package com.formation.project.computerDatabase.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.base.QCompany;
import com.formation.project.computerDatabase.base.QComputer;
import com.formation.project.computerDatabase.dao.repo.IComputerRepo;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Order;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.path.PathBuilder;

@Repository
public class ComputerDaoImpl implements IComputerDao {

	@Autowired
	IComputerRepo computerRepository;
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void save(Computer computer) {
		computerRepository.save(computer);
	}

	@Override
	public void delete(Computer computer) {
		computerRepository.delete(computer);		
	}

	@Override
	public Computer findOne(Long id) {
		return computerRepository.findOne(id);
	}

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
	
	@Override
	public boolean exists(Long id) {
		return computerRepository.exists(id);
	}

}
