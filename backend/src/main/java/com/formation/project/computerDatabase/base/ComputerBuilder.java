package com.formation.project.computerDatabase.base;

import java.util.Date;

public class ComputerBuilder {

	private Long id=null;
    
    private String name=null;
    
    private Date introduced=null;
    
    private Date discontinued=null;
    
    private Company company=null;
    
    public ComputerBuilder id(Long id) {
    	this.id = id;
    	return this;
    }
    
    public ComputerBuilder name(String name) {
    	this.name = name;
    	return this;
    }
	
    public ComputerBuilder introduced(Date introduced) {
    	this.introduced = introduced;
    	return this;
    }
    
    public ComputerBuilder discontinued(Date discontinued) {
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
