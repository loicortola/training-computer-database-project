package com.formation.project.computerDatabase.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.formation.project.computerDatabase.base.Stat;

@Repository
public class JpaStatsDao implements IStatsDao {
	
	@Autowired
	private StatRepository repo;

	@Override
	public void save(Stat stat) {
		repo.save(stat);
	}

}
