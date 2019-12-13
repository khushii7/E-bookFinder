package com.bookfinder.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String newpass = request.getParameter("npassword");
		String cpass = request.getParameter("cpassword");
		HttpSession session = request.getSession();
		System.out.print(newpass);
		System.out.print(cpass);
		try {
			if (newpass.equals(cpass)) {
				String enrollid=(String) session.getAttribute("enroll_id");
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/sl1_mini",
						"root", "MYSQLRoot_7");
				System.out.print("connecting...");
				PreparedStatement pt = con.prepareStatement("update students set password = '" + newpass + "' where enroll_id = ?");
				pt.setString(1, enrollid);
				int i = pt.executeUpdate();
				session.setAttribute("password", newpass);
				response.sendRedirect("profile.jsp");
			} else {
				response.sendRedirect("profile.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
}
