package com.formation.computerdatabase.controller;

import java.beans.PropertyEditorSupport;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.formation.computerdatabase.back.service.ComputerDatabaseService;
import com.formation.computerdatabase.back.service.UserService;
import com.formation.computerdatabase.core.domain.Company;
import com.formation.computerdatabase.core.domain.Computer;
import com.formation.computerdatabase.validator.ComputerForm;

@Controller
@RequestMapping("/addComputer")
public class AddComputerController {
	
	@Autowired
    private ComputerDatabaseService cs	= null;
    
	@Autowired
	private UserService us = null;
	
	private final static Logger logger = LoggerFactory.getLogger(AddComputerController.class);
		
    public AddComputerController() {
    	super();
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {
    	
    	//What we do with a company field in a form
    	binder.registerCustomEditor(Company.class, null, new PropertyEditorSupport() {
    	    public void setAsText(String value) {
    	    	if(value == null || value == "")
    	    		setValue(null);
    	    	else
    	            setValue(cs.getCompany(Long.parseLong(value)));
    	    }
    	    public String getAsText() {
    	    	if(getValue() == null)
    	    		return null;
    	        return ((Company) getValue()).getId().toString();
    	    }
    	});
    }
    
    @RequestMapping(method = RequestMethod.GET)
	public ModelAndView addComputer() {
    	logger.debug("Entering AddComputerController:GET");
		
    	ModelAndView mav = new ModelAndView("addComputer");
    	mav.addObject("computerForm", new ComputerForm());
    	mav.addObject("companies", cs.getCompaniesList());
    	
        return mav;
    }
    
    @RequestMapping(method = RequestMethod.POST)
	public ModelAndView submitAddComputer(@Valid @ModelAttribute("computerForm") ComputerForm computerForm, BindingResult result, RedirectAttributes redirectAttributes) {
    	
		logger.debug("Entering AddComputerController:POST");
    	
		ModelAndView mav = new ModelAndView("addComputer");
		
		Computer computer = computerForm.toComputer();
		
		
		if(result.hasErrors()) {
			mav.addObject("result",result);
			mav.addObject("computerForm", computerForm);
			mav.addObject("companies", cs.getCompaniesList());
		}
		else {
			try {
				cs.addComputer(computer);
				redirectAttributes.addFlashAttribute("submitAction","add");
				redirectAttributes.addFlashAttribute("computerName",computer.getName());
				
			} catch (IllegalArgumentException e) {
				logger.warn("WARNING in CoreServlet.submitAddComputer iae: {}", e.getMessage());
			}
			mav.setViewName("redirect:/dashboard.html");
		}
		
		return mav;
    }
}