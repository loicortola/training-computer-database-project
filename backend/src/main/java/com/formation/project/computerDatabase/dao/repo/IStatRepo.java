package com.formation.project.computerDatabase.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formation.project.computerDatabase.base.Stat;

@Repository
public interface IStatRepo extends JpaRepository<Stat, Long> {

}
