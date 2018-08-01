package com.ShopStop.ecommerce.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ShopStop.ecommerce.dao.Operation;
import com.ShopStop.ecommerce.pojo.UserAccount;
import com.ShopStop.ecommerce.service.BasicUtils;
 
@WebFilter(filterName = "cookieFilter", urlPatterns = { "/*" })
public class CookieFilter implements Filter {
 
    public CookieFilter() {
    }
 
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
 
    }
 
    @Override
    public void destroy() {
 
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
 
        UserAccount userInSession = BasicUtils.getLoginedUser(session);
        // 
        if (userInSession != null) {
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
            chain.doFilter(request, response);
            return;
        }
 
        // Connection was created in JDBCFilter.
        Connection conn = BasicUtils.getStoredConnection(request);
 
        // Flag check cookie
        String checked = (String) session.getAttribute("COOKIE_CHECKED");
        if (checked == null && conn != null) {
            String userName = BasicUtils.getUserNameInCookie(req);
            try {
                UserAccount user = Operation.findUser(conn, userName);
                BasicUtils.storeLoginedUser(session, user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // Mark checked Cookies.
            session.setAttribute("COOKIE_CHECKED", "CHECKED");
        }
 
        chain.doFilter(request, response);
    }
 
}