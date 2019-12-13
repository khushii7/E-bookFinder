package com.bookfinder.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("enroll_id");
		session.removeAttribute("name");
		session.removeAttribute("last_name");
		session.removeAttribute("phone");
		session.removeAttribute("email");
		session.removeAttribute("password");
		session.invalidate();
		//successfully logged out
		response.sendRedirect("home.jsp");
	}



}
