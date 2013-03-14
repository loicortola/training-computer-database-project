package com.formation.project.computerDatabase.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.formation.project.computerDatabase.base.Computers;
import com.formation.project.computerDatabase.base.TableSort;
import com.formation.project.computerDatabase.service.IComputerDatabaseService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private IComputerDatabaseService cs;
	private static final Integer RESULTS_PER_PAGE = 10;
	private final static Logger logger = LoggerFactory.getLogger(DashboardController.class);
	
	
	public DashboardController() {
        super();
       }	
	
	@RequestMapping(method = RequestMethod.GET)
	private ModelAndView dashboard(
									@RequestParam(value="searchCompanyName", defaultValue="") String searchCompanyName,
									@RequestParam(value="searchComputerName", defaultValue="") String searchComputerName,
									@RequestParam(value="sortBy", defaultValue="0") String sortBy,
									@RequestParam(value="page", defaultValue="1") String page
									) {
		
		logger.debug("Entering DashboardController");
		
		TableSort sortByEnum = null;
		Integer currentPage  = null;
		Long pageCount 		 = null;
		
		sortByEnum  = TableSort.fromInteger(Integer.parseInt(sortBy));
		currentPage = Integer.parseInt(page);
					
		Computers computers = cs.getComputers(currentPage, RESULTS_PER_PAGE, sortByEnum, searchComputerName, searchCompanyName);
		
		pageCount 	= ((Long) computers.getComputerCount()/RESULTS_PER_PAGE) + 1;
		
		
		ModelAndView mav = new ModelAndView("dashboard");
    	mav.addObject("computers", computers);
    	mav.addObject("pageCount", pageCount);
    	mav.addObject("currentPage", currentPage);
    	mav.addObject("resultsPerPage", RESULTS_PER_PAGE);
		
    	return mav;
	}
	
}