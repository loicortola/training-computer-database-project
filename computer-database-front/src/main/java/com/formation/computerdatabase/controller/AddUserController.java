package com.formation.computerdatabase.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.formation.computerdatabase.back.service.UserService;
import com.formation.computerdatabase.core.domain.Role;
import com.formation.computerdatabase.core.domain.User;

@Controller
@RequestMapping("/initUsers")
public class AddUserController {

	private final static Logger logger = LoggerFactory.getLogger(AddComputerController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView addComputer() {
    	logger.debug("Entering AddUserController:GET");
		User user = new User();
		user.setUsername("admin");
		user.setPassword("admin");
		user.setIsEnabled(true);
		user.setIsUnlocked(true);
		List<Role> authorities = new ArrayList<Role>();
		user.setAuthorities(authorities);
		userService.save(user);
    	ModelAndView mav = new ModelAndView("addUser");
    	
        return mav;
    }
	
	
}
