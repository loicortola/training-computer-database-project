package com.formation.project.computerDatabase.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.formation.project.computerDatabase.base.Stat;
import com.formation.project.computerDatabase.dao.repo.IStatRepo;

@Repository
public class StatDaoImpl implements IStatDao {

	@Autowired
	IStatRepo statRepository;
	
	@Override
	public void save(Stat stat) {
		statRepository.save(stat);		
	}

}
