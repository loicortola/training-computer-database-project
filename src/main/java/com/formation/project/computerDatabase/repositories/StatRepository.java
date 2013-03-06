package com.formation.project.computerDatabase.repositories;

import org.springframework.data.repository.Repository;

import com.formation.project.computerDatabase.base.Stat;

public interface StatRepository extends Repository<Stat, Long> {
	  Stat save(Stat entity);
}