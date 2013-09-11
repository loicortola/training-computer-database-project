package com.formation.computerdatabase.back.dao;

import org.springframework.data.repository.CrudRepository;

import com.formation.computerdatabase.back.dao.custom.ComputerDaoCustom;
import com.formation.computerdatabase.core.domain.Computer;

public interface ComputerDao extends CrudRepository<Computer, Long>, ComputerDaoCustom {

}
