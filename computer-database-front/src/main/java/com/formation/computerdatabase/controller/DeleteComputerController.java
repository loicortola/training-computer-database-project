package com.formation.computerdatabase.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.formation.computerdatabase.back.service.ComputerDatabaseService;

@Controller
@RequestMapping("/deleteComputer")
public class DeleteComputerController {
	@Autowired
    private ComputerDatabaseService cs	= null;
    
	private final static Logger logger = LoggerFactory.getLogger(DeleteComputerController.class);
	
    public DeleteComputerController() {
    	super();
    }

    @RequestMapping(method = RequestMethod.GET)
   	public String deleteComputer(Long id) {
    	logger.debug("Warning in DeleteComputerController:GET : shouldn't be here!");
    	return "redirect:/dashboard.html";
    }
	
    @RequestMapping(method = RequestMethod.POST)
	private ModelAndView submitDeleteComputer(@RequestParam(value="id",required=true) Long id, RedirectAttributes redirectAttributes) {
    	logger.debug("Entering DeleteComputerController:POST");
    	
		ModelAndView mav = new ModelAndView("redirect:/dashboard.html");
	    
		try {
			cs.deleteComputer(id);
			redirectAttributes.addFlashAttribute("submitAction","delete");
		} catch (IllegalArgumentException e) {
			logger.warn("WARNING in DeleteComputer.submitDeleteComputer iae: {}", e.getMessage());
			mav.setViewName("editComputer");
		}
		
		return mav;		
	}
	
}