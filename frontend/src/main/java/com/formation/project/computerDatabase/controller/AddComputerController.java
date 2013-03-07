package com.formation.project.computerDatabase.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.base.ComputerBuilder;
import com.formation.project.computerDatabase.service.IComputerDatabaseService;

@Controller
@RequestMapping("/addComputer")
public class AddComputerController {
	
	@Autowired
    private IComputerDatabaseService cs	= null;
    
    public AddComputerController() {
    	super();
    }
    
    @RequestMapping(method = RequestMethod.GET)
	public ModelAndView addComputer() {
    	System.out.println("Entering AddComputerController:GET");
		
    	ModelAndView mav = new ModelAndView("addComputer");
    	mav.addObject("computer", new Computer());
    	mav.addObject("companies", cs.getCompaniesList());
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST)
	public ModelAndView submitAddComputer(String name, Long company, String introduced, String discontinued) {
    	
    	System.out.println("Entering AddComputerController:POST");
    	
    	Computer computer 		= null;
    	Date introducedDate 	= null;
		Date discontinuedDate 	= null;
		
    	DateFormat dateFormat	= new SimpleDateFormat("yyyy-MM-dd");
		Boolean error			= false;
		
		
		Company finalCompany	= cs.getCompany(company);
		
		ModelAndView mav = new ModelAndView("addComputer");
    	
		if(name == null || name == "") 
			mav.addObject("nameError", "Name field is required");

		if(company == null)
			mav.addObject("companyError", "Please select a company");
		
	    try {
			introducedDate = dateFormat.parse(introduced);	
		} catch (ParseException e) {
			System.err.println("Error in CoreServlet.submitAddComputer pe: " + e.getMessage());
			mav.addObject("introducedError", "Introduced date is not valid");
		}
		
		try {
			discontinuedDate = dateFormat.parse(discontinued);	
		} catch (ParseException e1) {
			System.out.println("Warning in CoreServlet.submitAddComputer: No discontinued date specified");
		}
		
		computer = new ComputerBuilder()
		.name(name)
		.introduced(introducedDate)
		.discontinued(discontinuedDate)
		.company(finalCompany)
		.build();
		
		try {			
			cs.addComputer(computer);	
		} catch (IllegalArgumentException e) {
			System.err.println("Error in CoreServlet.submitAddComputer iae: " + e.getMessage());
			error = true;
		}
		if(error) {
			mav.addObject("computer", computer);
			mav.addObject("companies", cs.getCompaniesList());
		}
		else {
			mav.setViewName("redirect:/dashboard.html");
		}
		
		return mav;
    }
}