package com.formation.computerdatabase.back.dao.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.formation.computerdatabase.core.domain.Computer;

public interface ComputerDaoCustom {

	public abstract Page<Computer> getComputers(Pageable page,
			String computerName, String companyName);

	public abstract List<Computer> getComputers(String computerName,
			String companyName);

}