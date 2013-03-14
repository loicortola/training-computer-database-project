package com.formation.project.computerDatabase.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.project.computerDatabase.base.Stat;

public interface IStatDao extends JpaRepository<Stat, Long> {

}
