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
import com.ShopStop.ecommerce.pojo.Product;
import com.ShopStop.ecommerce.service.BasicUtils;
 
@WebServlet(urlPatterns = { "/buyProducts" })
public class BuyProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public BuyProductServlet() {
        super();
    }
 
    // Show product Buy page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = BasicUtils.getStoredConnection(request);
 
        String code = (String) request.getParameter("code");
 
        Product product = null;
 
        String errorString = null;
 
        try {
            product = Operation.findProduct(conn, code);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        // If no error.
        // The product does not exist to buy
        // Redirect to productList page.
        if (errorString != null && product == null) {
            response.sendRedirect(request.getServletPath() + "/productList");
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("product", product);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/buyProductView.jsp");
        dispatcher.forward(request, response);
 
    }
 
    
}





























//After the user modifies the product information, and click Submit.
// This method will be executed.
//@Override
//protected void doPost(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//    Connection conn = BasicUtils.getStoredConnection(request);
//
//    String code = (String) request.getParameter("code");
//    String name = (String) request.getParameter("name");
//    String priceStr = (String) request.getParameter("price");
//    float price = 0;
//    try {
//        price = Float.parseFloat(priceStr);
//    } catch (Exception e) {
//    }
//    Product product = new Product(code, name, price);
//
//    String errorString = null;
//
//    try {
//        DBUtils.deleteProduct(conn, product);
//    } catch (SQLException e) {
//        e.printStackTrace();
//        errorString = e.getMessage();
//    }
//    // Store infomation to request attribute, before forward to views.
//    request.setAttribute("errorString", errorString);
//    request.setAttribute("product", product);
//
//    // If error, forward to Edit page.
//    if (errorString != null) {
//        RequestDispatcher dispatcher = request.getServletContext()
//                .getRequestDispatcher("/WEB-INF/views/buyProductView.jsp");
//        dispatcher.forward(request, response);
//    }
//    // If everything nice.
//    // Redirect to the product listing page.
//    else {
//        response.sendRedirect(request.getContextPath() + "/productList");
//    }
//}
