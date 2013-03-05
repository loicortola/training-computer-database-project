package com.formation.project.computerDatabase.controller;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.formation.project.computerDatabase.conf.DBInit;

@Controller
public class InitServlet implements ServletContextAware {

	@Autowired
	ServletContext sc;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/init")
	protected ModelAndView doGet() {
		 try {
			DBInit.initDatabase(sc,jdbcTemplate.getDataSource().getConnection());
		} catch (SQLException e) {
			System.err.println("error in InitServlet while initiating database: " + e.getMessage());
		}
		
		return new ModelAndView("redirect:/dashboard.html");
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		
	}

}
