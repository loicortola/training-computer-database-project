package com.formation.project.computerDatabase.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.formation.project.computerDatabase.base.Router;
import com.formation.project.computerDatabase.service.IComputerDatabaseService;
import com.formation.project.computerDatabase.service.ServiceFactory;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IComputerDatabaseService	cs 	= null;
	private HttpSession session 			= null;
    private RequestDispatcher rd 			= null;
    private Router ar						= null;
    
    public DashboardServlet() {
        super();
       }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("======================================================");
		System.out.println("CoreServlet: Entering doGet");
		
		initParams(req);
		dashboard(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("======================================================");
		System.out.println("CoreServlet: Entering doPost");
		
		initParams(req);
		dashboard(req, res);
	}
	
	private void dashboard(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String searchName = req.getParameter("searchName");
		String sortBy = req.getParameter("sortBy");
		
		if(searchName == null)
			searchName = "";
		if(sortBy == null)
			sortBy = "name1";
		
		Integer computerCount = cs.getComputerCount(searchName);
		Integer resultsPerPage = 10;
		Integer pageCount = ((Integer) computerCount/resultsPerPage) + 1;
		Integer currentPage = 1;
		if(req.getParameter("page") != null) {
			currentPage = Integer.parseInt(req.getParameter("page"));
			if(currentPage < 1 || currentPage > pageCount)
				currentPage = 1;
		}
		
		req.setAttribute("computers", cs.getComputers(currentPage, resultsPerPage, sortBy, searchName));
		req.setAttribute("computerCount", computerCount);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("resultsPerPage", resultsPerPage);
		req.setAttribute("sortBy", sortBy);
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
	
}