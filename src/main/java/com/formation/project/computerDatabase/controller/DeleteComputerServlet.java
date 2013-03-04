package com.formation.project.computerDatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.formation.project.computerDatabase.service.IComputerDatabaseService;

@Controller
@RequestMapping("/deleteComputer")
public class DeleteComputerServlet {
	@Autowired
    private IComputerDatabaseService	cs 	= null;
    
    public DeleteComputerServlet() {
        super();
       }

    @RequestMapping(method = RequestMethod.GET)
   	public String deleteComputer(Integer id) {
    	System.out.println("Warning in DeleteComputerServlet:GET : shouldn't be here!");
    	return "redirect:/dashboard.html";
       }
	
    @RequestMapping(method = RequestMethod.POST)
	private ModelAndView submitDeleteComputer(Integer id) {
    	System.out.println("Entering DeleteComputerServlet:POST");
    	
		ModelAndView mav = new ModelAndView("redirect:/dashboard.html");
	    
		if(id == null) {
			System.err.println("Warning: Computer id was not right in DeleteComputerServlet.POST");
		}		
		else {
			try {				
				cs.deleteComputer(id);
			} catch (IllegalArgumentException e) {
				System.err.println("Error in DeleteComputer.submitDeleteComputer iae: " + e.getMessage());
				mav.setViewName("editComputer");
			}
		}
		
		return mav;		
	}
	
}