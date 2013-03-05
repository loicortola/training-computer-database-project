package com.formation.project.computerDatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.formation.project.computerDatabase.base.TableSort;
import com.formation.project.computerDatabase.service.IComputerDatabaseService;

@Controller
@RequestMapping("/dashboard")
public class DashboardServlet {
	
	@Autowired
	private IComputerDatabaseService cs;

	public DashboardServlet() {
        super();
       }	
	
	@RequestMapping(method = RequestMethod.GET)
	private ModelAndView dashboard(String searchName, String sortBy, String page) {
		System.out.println("Entering DashboardServlet");
		TableSort sortByEnum = null;
		
		if(searchName == null)
			searchName = "";
		if(sortBy != null)
			sortByEnum = TableSort.fromInteger(Integer.parseInt(sortBy));
		if(sortBy == null)
			sortByEnum = TableSort.NAME_ASC;
		if(cs == null)
			System.out.println("cs null");
		Integer computerCount = cs.getComputerCount(searchName);
		Integer resultsPerPage = 10;
		Integer pageCount = ((Integer) computerCount/resultsPerPage) + 1;
		Integer currentPage = 1;
		if(page != null) {
			currentPage = Integer.parseInt(page);
			if(currentPage < 1 || currentPage > pageCount)
				currentPage = 1;
		}
		
		ModelAndView mav = new ModelAndView("dashboard");
    	mav.addObject("computers", cs.getComputers(currentPage, resultsPerPage, sortByEnum, searchName));
    	mav.addObject("pageCount", pageCount);
    	mav.addObject("currentPage", currentPage);
    	mav.addObject("resultsPerPage", resultsPerPage);
		
    	return mav;
	}
	
}