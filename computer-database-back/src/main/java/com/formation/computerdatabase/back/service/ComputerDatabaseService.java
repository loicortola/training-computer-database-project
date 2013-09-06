package com.formation.computerdatabase.back.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import com.formation.computerdatabase.core.domain.Company;
import com.formation.computerdatabase.core.domain.Computer;

public interface ComputerDatabaseService {

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	void addComputer(Computer computer);

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	void updateComputer(Computer computer);

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	void deleteComputer(Long computerId);

	@PreAuthorize("hasAuthority('ROLE_BASIC')")
	Computer getComputer(Long computerId);
	
	@PreAuthorize("hasAuthority('ROLE_BASIC')")
	Company getCompany(Long companyId);

	@PreAuthorize("hasAuthority('ROLE_BASIC')")
	List<Company> getCompaniesList();

	@PreAuthorize("hasAuthority('ROLE_BASIC')")
	Page<Computer> getComputers(Pageable page, String computerName, String companyName);

}