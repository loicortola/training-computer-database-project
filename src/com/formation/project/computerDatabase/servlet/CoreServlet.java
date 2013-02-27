package com.formation.project.computerDatabase.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CoreServlet")
public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID 	= 1L;
    private RequestDispatcher rd 				= null;
    
    public CoreServlet() {
        super();
       }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("======================================================");
		System.out.println("CoreServlet: Entering doGet");
		
		//With no default action, forwarding to the dashboard
		if(req.getParameter("action") == null)	
		{
			//defaultAction(req, res);
			rd = req.getRequestDispatcher("DashboardServlet");
			rd.forward(req, res);
		}
		else
		{
			String reqAction = req.getParameter("action");
			System.out.println("Action requested: " + reqAction);
			if("addComputer".equals(reqAction))
			{
				//addComputer(req, res);
				rd = req.getRequestDispatcher("ComputerServlet");
				rd.forward(req, res);
			}
			if("editComputer".equals(reqAction))
			{
				//editComputer(req, res);
				rd = req.getRequestDispatcher("ComputerServlet");
				rd.forward(req, res);
			}
		}
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("======================================================");
		System.out.println("CoreServlet: Entering doPost");
		
		//With no default action, forwarding to the dashboard
		if(req.getParameter("action") == null)	
		{
			//defaultAction(req, res);
			rd = req.getRequestDispatcher("DashboardServlet");
			rd.forward(req, res);
		}
		else
		{
			String reqAction = req.getParameter("action");
			System.out.println("Action requested: " + reqAction);
			if("submitAddComputer".equals(reqAction))
			{
				rd = req.getRequestDispatcher("ComputerServlet");
				rd.forward(req, res);
			}
			if("submitEditComputer".equals(reqAction))
			{
				rd = req.getRequestDispatcher("ComputerServlet");
				rd.forward(req, res);
			}
			if("submitDeleteComputer".equals(reqAction))
			{
				rd = req.getRequestDispatcher("ComputerServlet");
				rd.forward(req, res);
			}
		}
	}
	
}