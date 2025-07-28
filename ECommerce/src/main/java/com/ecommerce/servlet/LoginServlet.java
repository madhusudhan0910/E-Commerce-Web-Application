package com.ecommerce.servlet;

import com.ecommerce.dao.DBConnection;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");  // Use email (matches form)
        String password = request.getParameter("password");

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT id, password FROM users WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String storedPassword = rs.getString("password");

                    if (storedPassword.equals(password)) { // If passwords match
                        HttpSession session = request.getSession();
                        session.setAttribute("userId", rs.getInt("id"));
                        session.setAttribute("email", email);
                        session.setMaxInactiveInterval(30 * 60); // 30 min session timeout

                        response.sendRedirect("ShopServlet"); // Redirect to shop
                    } else {
                        response.sendRedirect("login.jsp?error=Invalid Credentials");
                    }
                } else {
                    response.sendRedirect("login.jsp?error=Invalid Credentials");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=Database Error");
        }
    }
}
