package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EmployeeLogin
 */
@WebServlet("/EmployeeLogin")
public class EmployeeLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Handle ch = new Handle();
		int valid = ch.userExist(username, password, 0);
		
		if (valid < 0) {
			//This is where you will navigate to a employee view page
			response.sendRedirect("EmployeeLogin.jsp");
		} else {
			//This is where you will recurse to the login page
			HttpSession session = request.getSession();
			session.setAttribute("user", username);
			switch(valid) {
			case 0:
				response.sendRedirect("manager.jsp");
				break;	
			case 1:
				response.sendRedirect("maintainer.jsp");
				break;
			case 2:
				response.sendRedirect("shopper.jsp");
				break;
			case 3:
				response.sendRedirect("driver.jsp");
				break;
			default:
				response.sendRedirect("EmployeeLogin.jsp");
				break;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
