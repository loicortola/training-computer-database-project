package com.formation.project.computerDatabase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

	@RequestMapping("/login")
	public String login() {
	    return "login";
	}
}
