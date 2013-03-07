package com.formation.project.computerDatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.project.computerDatabase.base.Stat;

public interface StatRepository extends JpaRepository<Stat, Long> {
}