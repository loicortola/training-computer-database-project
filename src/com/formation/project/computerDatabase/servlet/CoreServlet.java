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

@WebServlet("/CoreServlet")
public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HttpSession session 	= null;
    private RequestDispatcher rd 	= null;
    public CoreServlet() {
        super();
       }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("======================================================");
		System.out.println("CoreServlet: Entering doGet");
		
		session = req.getSession(true);

		//Getting the routerBean from the user's session
		Router ar 	= (Router) session.getAttribute("routerBean");
		if(ar == null)
		{
			System.out.println("First time creating a routerBean instance");
    		ar = new Router();
			session.setAttribute("routerBean", ar);
		}
		//With no default action, forwarding to the dashboard
		if(req.getParameter("action") == null)	
		{
			ar.setUrl("dashboard.jsp");
			rd = req.getRequestDispatcher("jsp/index.jsp");
			rd.forward(req, res);
		}
		else
		{
			if("login".equals(req.getParameter("action")))
			{
			}
		}
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("======================================================");
		System.out.println("CoreServlet: Entering doPost");
	}

}