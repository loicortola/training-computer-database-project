package com.formation.project.computerDatabase.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.formation.project.computerDatabase.base.Computer;
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
	
	private Sort getSort(TableSort sortBy) {
		return new Sort(sortBy.isAsc() 
				? Sort.Direction.ASC
				: Sort.Direction.DESC, 
				sortBy.getSortString());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	private ModelAndView dashboard(
									@RequestParam(value="searchCompanyName", defaultValue="") String searchCompanyName,
									@RequestParam(value="searchComputerName", defaultValue="") String searchComputerName,
									@RequestParam(value="sortBy", defaultValue="0") Integer sortBy,
									@RequestParam(value="page", defaultValue="1") Integer currentPage
									) {
		
		logger.debug("Entering DashboardController");
		
		TableSort sortByEnum = null;
		
		sortByEnum  = TableSort.fromInteger(sortBy);
		
		Pageable page = new PageRequest(currentPage-1, RESULTS_PER_PAGE, getSort(sortByEnum));
		
		Page<Computer> computersPage = cs.getComputers(page, searchComputerName, searchCompanyName);
		
		ModelAndView mav = new ModelAndView("dashboard");
    	mav.addObject("computers", computersPage);
		mav.addObject("sortBy",sortByEnum);
		
    	return mav;
	}
	
}