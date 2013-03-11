package com.formation.project.computerDatabase.controller;

import java.beans.PropertyEditorSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.service.IComputerDatabaseService;
import com.formation.project.computerDatabase.validator.ComputerForm;

@Controller
@RequestMapping("/editComputer")
public class EditComputerController {
	@Autowired
    private IComputerDatabaseService	cs 	= null;
    
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
   	public ModelAndView editComputer(Long editId) {
    	System.out.println("Entering EditComputerController:GET");

       	ModelAndView mav  = new ModelAndView("editComputer");
       	Computer computer = cs.getComputer(editId);
    	
		if(computer == null) {
			System.err.println("Warning: Computer id was not right in EditComputerController.GET");
			mav.setViewName("redirect:/dashboard.html");
		}
		else {
			
			mav.addObject("computerForm", new ComputerForm(computer));
       		mav.addObject("companies", cs.getCompaniesList());
		}
           return mav;
       }
    
    @RequestMapping(method = RequestMethod.POST)
	private ModelAndView submitEditComputer(@Valid @ModelAttribute("computerForm") ComputerForm computerForm, BindingResult result) {
    	System.out.println("Entering EditComputerController:POST");
    	
		ModelAndView mav = new ModelAndView("editComputer");
	    		
		if(computerForm.getId() == null) {
			System.err.println("Warning: Computer id was not right in EditComputerController.POST");
		}		
		else {
			if(result.hasErrors()) {
				mav.addObject("result",result);
				mav.addObject("computerForm", computerForm);
				mav.addObject("companies", cs.getCompaniesList());
			}
			else {
				try {
					cs.updateComputer(computerForm.toComputer());
				} catch (IllegalArgumentException e) {
					System.err.println("Error in CoreServlet.submitEditComputer iae: " + e.getMessage());
				}
				mav.setViewName("redirect:/dashboard.html");
			}
		}
		
		return mav;		
	}
	
}