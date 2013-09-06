package com.formation.computerdatabase.back.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.computerdatabase.core.domain.Stat;

public interface StatDao extends JpaRepository<Stat, Long> {

}
