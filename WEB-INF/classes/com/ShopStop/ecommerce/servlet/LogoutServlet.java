package com.ShopStop.ecommerce.servlet;

import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/logout")

public class LogoutServlet extends HttpServlet {
	
	// when logout is clicked it will delete the session 
	// for the user and redirect to login page

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        request.getSession().invalidate();
	        response.sendRedirect(request.getContextPath() + "/login");
	   }

}

