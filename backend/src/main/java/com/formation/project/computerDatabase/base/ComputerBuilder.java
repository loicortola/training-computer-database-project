package com.formation.project.computerDatabase.base;

import org.joda.time.LocalDate;

public class ComputerBuilder {

	private Long id=null;
    
    private String name=null;
    
    private LocalDate introduced=null;
    
    private LocalDate discontinued=null;
    
    private Company company=null;
    
    public ComputerBuilder id(Long id) {
    	this.id = id;
    	return this;
    }
    
    public ComputerBuilder name(String name) {
    	this.name = name;
    	return this;
    }
	
    public ComputerBuilder introduced(LocalDate introduced) {
    	this.introduced = introduced;
    	return this;
    }
    
    public ComputerBuilder discontinued(LocalDate discontinued) {
    	this.discontinued = discontinued;
    	return this;
    }
    
    public ComputerBuilder company(Company company) {
    	this.company = company;
    	return this;
    }
    
    public Computer build() {

    	Computer computer = new Computer();
    	computer.setId(id);
    	computer.setName(name);
    	computer.setIntroduced(introduced);
    	computer.setDiscontinued(discontinued);
    	computer.setCompany(company);
    	computer.setIsVisible(true);
    	return computer;
    }
    
}
