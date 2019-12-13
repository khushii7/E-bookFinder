package com.bookfinder.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ReturnBookU")
public class ReturnBookU extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String url = "jdbc:mysql://localhost:3306/sl1_mini";
	String username = "root";
	String password = "MYSQLRoot_7";
	//private Connection con;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String book_id = request.getParameter("book_id");
		HttpSession session = request.getSession();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(url, username, password);
			System.out.print("connecting...");
			String enroll = session.getAttribute("enroll_id").toString();
			
			System.out.print(enroll);
			int b_id = Integer.parseInt(book_id);
			PreparedStatement pt = con.prepareStatement("delete from books_borrowed where book_id = ? and enroll_id = ?");
			pt.setInt(1, b_id);
			pt.setString(2, enroll);
			int check = pt.executeUpdate(); 

			response.sendRedirect("Borrowed_books.jsp");
			 

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
