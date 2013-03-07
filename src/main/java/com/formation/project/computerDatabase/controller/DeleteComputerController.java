package com.formation.project.computerDatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.formation.project.computerDatabase.service.IComputerDatabaseService;

@Controller
@RequestMapping("/deleteComputer")
public class DeleteComputerController {
	@Autowired
    private IComputerDatabaseService cs	= null;
    
    public DeleteComputerController() {
    	super();
    }

    @RequestMapping(method = RequestMethod.GET)
   	public String deleteComputer(Long id) {
    	System.out.println("Warning in DeleteComputerController:GET : shouldn't be here!");
    	return "redirect:/dashboard.html";
    }
	
    @RequestMapping(method = RequestMethod.POST)
	private ModelAndView submitDeleteComputer(@RequestParam(value="id",required=true) Long id) {
    	System.out.println("Entering DeleteComputerController:POST");
    	
		ModelAndView mav = new ModelAndView("redirect:/dashboard.html");
	    
		try {				
			cs.deleteComputer(id);
		} catch (IllegalArgumentException e) {
			System.err.println("Error in DeleteComputer.submitDeleteComputer iae: " + e.getMessage());
			mav.setViewName("editComputer");
		}
		
		return mav;		
	}
	
}