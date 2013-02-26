package com.formation.project.computerDatabase.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.formation.project.computerDatabase.base.Company;
import com.formation.project.computerDatabase.base.Computer;
import com.formation.project.computerDatabase.base.ComputerBuilder;
import com.formation.project.computerDatabase.base.Router;
import com.formation.project.computerDatabase.service.IComputerDatabaseService;
import com.formation.project.computerDatabase.service.ServiceFactory;

@WebServlet("/CoreServlet")
public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IComputerDatabaseService	cs 	= null;
	private HttpSession session 			= null;
    private RequestDispatcher rd 			= null;
    private Router ar						= null;
    
    public CoreServlet() {
        super();
       }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("======================================================");
		System.out.println("CoreServlet: Entering doGet");
		
		initParams(req);
		//With no default action, forwarding to the dashboard
		if(req.getParameter("action") == null)	
		{
			defaultAction(req, res);
		}
		else
		{
			String reqAction = req.getParameter("action");
			System.out.println("Action requested: " + reqAction);
			if("addComputer".equals(reqAction))
			{
				addComputer(req, res);
			}
			if("editComputer".equals(reqAction))
			{
				editComputer(req, res);
			}
		}
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("======================================================");
		System.out.println("CoreServlet: Entering doPost");
		
		initParams(req);
		
		//With no default action, forwarding to the dashboard
		if(req.getParameter("action") == null)	
		{
			defaultAction(req, res);
		}
		else
		{
			String reqAction = req.getParameter("action");
			System.out.println("Action requested: " + reqAction);
			if("submitAddComputer".equals(reqAction))
			{
				submitAddComputer(req, res);
			}
			if("submitEditComputer".equals(reqAction))
			{
				submitEditComputer(req, res);
			}
		}
	}
	
	private void defaultAction(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if(req.getParameter("searchName") == null)
			req.setAttribute("computers", cs.getComputers());
		else
			req.setAttribute("computers", cs.getComputers(req.getParameter("searchName")));
		
		ar.setUrl("dashboard.jsp");
		
		rd = req.getRequestDispatcher("jsp/index.jsp");
		rd.forward(req, res);
	}

	private void initParams(HttpServletRequest req) {
		
		session = req.getSession(true);
		cs		= ServiceFactory.getService();
		
		//Getting the routerBean from the user's session
		ar 	= (Router) session.getAttribute("routerBean");
		if(ar == null)
		{
			System.out.println("First time creating a routerBean instance");
    		ar = new Router();
			session.setAttribute("routerBean", ar);
		}
	}
	
	private void addComputer(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setAttribute("computer",new Computer());
		req.setAttribute("companies", cs.getCompaniesList());
		ar.setUrl("addComputer.jsp");
		
		rd = req.getRequestDispatcher("jsp/index.jsp");
		rd.forward(req, res);
	}
	
	private void editComputer(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		DateFormat dateFormat	= new SimpleDateFormat("yyyy-MM-dd");
		Boolean error			= false;
		
		Computer computer 		= cs.getComputer(Integer.parseInt(req.getParameter("id")));
		
		if(computer == null) {
			defaultAction(req,res);	
		}
		else {
			req.setAttribute("computer", computer);
			req.setAttribute("companies", cs.getCompaniesList());
			ar.setUrl("editComputer.jsp");
			rd = req.getRequestDispatcher("jsp/index.jsp");
			rd.forward(req, res);
		}
		
	}

	private void submitAddComputer(HttpServletRequest req, HttpServletResponse res) throws ServletException,	IOException {
		Computer computer 		= null;
		DateFormat dateFormat	= new SimpleDateFormat("yyyy-MM-dd");
		Boolean error			= false;
		
		String name			= req.getParameter("name");
		Date introduced 	= null;
		Date discontinued 	= null;
		Company company		= cs.getCompany(Integer.parseInt(req.getParameter("company")));
		
		if(name == null || name == "")
			req.setAttribute("nameError", "Name field is required");
		if(company == null)
			req.setAttribute("companyError", "Please select a company");
		try {
			introduced	 	= dateFormat.parse(req.getParameter("introduced"));	
		} catch (ParseException e) {
			System.err.println("Error in CoreServlet.submitAddComputer pe: " + e.getMessage());
			req.setAttribute("introducedError", "Introduced date is not valid");
		}
		
		try {
			discontinued = dateFormat.parse(req.getParameter("discontinued"));
		} catch (ParseException e1) {
			System.out.println("Warning in CoreServlet.submitAddComputer: No discontinued date specified");
		}
		
		computer = new ComputerBuilder()
		.name(name)
		.introduced(introduced)
		.discontinued(discontinued)
		.company(company)
		.build();
		
		try {			
			cs.addComputer(computer);	
		} catch (IllegalArgumentException e) {
			System.err.println("Error in CoreServlet.submitAddComputer iae: " + e.getMessage());
			error = true;
		}
			if(error) {
				req.setAttribute("computer", computer);
				req.setAttribute("companies", cs.getCompaniesList());
				ar.setUrl("addComputer.jsp");				
				rd = req.getRequestDispatcher("jsp/index.jsp");
				rd.forward(req, res);
			}
			else {
				defaultAction(req,res);
			}
	}
	
	private void submitEditComputer(HttpServletRequest req, HttpServletResponse res) throws ServletException,	IOException {
		Computer computer 		= null;
		DateFormat dateFormat	= new SimpleDateFormat("yyyy-MM-dd");
		Boolean error			= false;
		
		Integer id			= Integer.parseInt(req.getParameter("id"));
		String name			= req.getParameter("name");
		Date introduced 	= null;
		Date discontinued 	= null;
		Company company		= cs.getCompany(Integer.parseInt(req.getParameter("company")));
		
		if(id == null) {
			defaultAction(req,res);
		}
		else {
			if(name == null || name == "")
				req.setAttribute("nameError", "Name field is required");
			if(company == null)
				req.setAttribute("companyError", "Please select a company");
			try {
				introduced	 	= dateFormat.parse(req.getParameter("introduced"));	
			} catch (ParseException e) {
				System.err.println("Error in CoreServlet.submitEditComputer pe: " + e.getMessage());
				req.setAttribute("introducedError", "Introduced date is not valid");
			}
			
			try {
				discontinued = dateFormat.parse(req.getParameter("discontinued"));
			} catch (ParseException e1) {
				System.out.println("Warning in CoreServlet.submitEditComputer: No discontinued date specified");
			}
			
			computer = new ComputerBuilder()
			.id(id)
			.name(name)
			.introduced(introduced)
			.discontinued(discontinued)
			.company(company)
			.build();
			
			try {				
				cs.updateComputer(computer);
			} catch (IllegalArgumentException e) {
				System.err.println("Error in CoreServlet.submitEditComputer iae: " + e.getMessage());
				error = true;
			}
				if(error) {
					req.setAttribute("computer", computer);
					req.setAttribute("companies", cs.getCompaniesList());
					ar.setUrl("editComputer.jsp");					
					rd = req.getRequestDispatcher("jsp/index.jsp");
					rd.forward(req, res);
				}
				else {
					defaultAction(req,res);
				}
		}
	}
	
}