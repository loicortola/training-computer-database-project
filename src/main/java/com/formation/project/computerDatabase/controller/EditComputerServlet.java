package com.formation.project.computerDatabase.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.base.ComputerBuilder;
import com.formation.project.computerDatabase.service.IComputerDatabaseService;

@Controller
@RequestMapping("/editComputer")
public class EditComputerServlet {
	@Autowired
    private IComputerDatabaseService	cs 	= null;
    
    public EditComputerServlet() {
        super();
       }

    @RequestMapping(method = RequestMethod.GET)
   	public ModelAndView editComputer(Integer id) {
    	System.out.println("Entering EditComputerServlet:GET");

       	ModelAndView mav  = new ModelAndView("editComputer");
       	Computer computer = cs.getComputer(id);
		
		if(computer == null) {
			System.err.println("Warning: Computer id was not right in EditComputerServlet.GET");
			mav.setViewName("redirect:/dashboard.html");
		}
		else {
			mav.addObject("computer", computer);
       		mav.addObject("companies", cs.getCompaniesList());
		}
           return mav;
       }
	
    @RequestMapping(method = RequestMethod.POST)
	private ModelAndView submitEditComputer(Integer id, 
											String name, 
											@RequestParam(value="company") Integer idCompany, 
											@RequestParam(value="introduced") String introducedStr, 
											@RequestParam(value="discontinued") String discontinuedStr) {
    	System.out.println("Entering EditComputerServlet:POST");
    	Computer computer 		= null;
		DateFormat dateFormat	= new SimpleDateFormat("yyyy-MM-dd");
		Boolean error			= false;
		
		ModelAndView mav = new ModelAndView("editComputer");
	    
		Date introducedDate 	= null;
		Date discontinuedDate 	= null;
		Company company	= cs.getCompany(idCompany);
		
		if(id == null) {
			System.err.println("Warning: Computer id was not right in EditComputerServlet.POST");
		}
		
		else {
			if(name == null || name == "")
				mav.addObject("nameError", "Name field is required");    	
			if(company == null)
				mav.addObject("companyError", "Please select a company");
		    try {
				introducedDate = dateFormat.parse(introducedStr);	
			} catch (ParseException e) {
				System.err.println("Error in CoreServlet.submitEditComputer pe: " + e.getMessage());
				mav.addObject("introducedError", "Introduced date is not valid");
			}
			
		    try {
				discontinuedDate = dateFormat.parse(discontinuedStr);	
			} catch (ParseException e1) {
				System.out.println("Warning in CoreServlet.submitEditComputer: No discontinued date specified");
			}
			
			computer = new ComputerBuilder()
			.id(id)
			.name(name)
			.introduced(introducedDate)
			.discontinued(discontinuedDate)
			.company(company)
			.build();
			
			try {				
				cs.updateComputer(computer);
			} catch (IllegalArgumentException e) {
				System.err.println("Error in CoreServlet.submitEditComputer iae: " + e.getMessage());
				error = true;
			}
			if(error) {
				mav.addObject("computer", computer);
				mav.addObject("companies", cs.getCompaniesList());
			}
			else {
				mav.setViewName("redirect:/dashboard.html");
			}
		}
		
		return mav;		
	}
	
}