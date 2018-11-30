package com.primetgi.org.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.primetgi.org.dao.LoginDAO;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.setAttribute("userName", userName);
		}

		RequestDispatcher requestDispatcher = null;

		try {
			if (LoginDAO.validate(userName, password)) {
				requestDispatcher = request.getRequestDispatcher("views/welcome.jsp");
				requestDispatcher.forward(request, response);
			} else {
				out.print("Invalid User Name or password");
				requestDispatcher = request.getRequestDispatcher("views/index.jsp");
				requestDispatcher.include(request, response);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
