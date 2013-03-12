package com.formation.project.computerDatabase.validator;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.base.Computer;

public class ComputerForm {

	private Long id;
	@NotNull
    @NotEmpty
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate introduced;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate discontinued;
	private Company company;
	
	public ComputerForm(Computer computer) {
		this.id = computer.getId();
		this.name = computer.getName();
		this.introduced = computer.getIntroduced();
		this.discontinued = computer.getDiscontinued();
		this.company = computer.getCompany();
	}
	
	public ComputerForm() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getIntroduced() {
		return introduced;
	}
	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}
	public LocalDate getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}
	
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public Computer toComputer() {
		Computer computer = new Computer();
		computer.setId(id);
		computer.setName(name);
		computer.setIntroduced(introduced);
		computer.setDiscontinued(discontinued);
		computer.setIsVisible(true);
		computer.setCompany(company);
		return computer;
	}

}
