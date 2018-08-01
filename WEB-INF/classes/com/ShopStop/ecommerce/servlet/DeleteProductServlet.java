package com.ShopStop.ecommerce.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ShopStop.ecommerce.dao.Operation;
import com.ShopStop.ecommerce.service.BasicUtils;
 
@WebServlet(urlPatterns = { "/deleteProduct" })
public class DeleteProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public DeleteProductServlet() {
        //super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = BasicUtils.getStoredConnection(request);
 
        String code = (String) request.getParameter("code");
 
        String errorString = null;
 
        try {
            Operation.deleteProduct(conn, code);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } 
         
        // If has an error, redirecte to the error page.
        if (errorString != null) {
            // Store the information in the request attribute, before forward to views.
            request.setAttribute("errorString", errorString);
            // 
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/error.jsp");
            dispatcher.forward(request, response);
        }
             
        else {
            //response.sendRedirect(request.getContextPath() + "/orderConfirmed");
        	 RequestDispatcher dispatcher = request.getServletContext()
                     .getRequestDispatcher("/WEB-INF/views/orderConfirmed.jsp");
             dispatcher.forward(request, response);
        }
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}