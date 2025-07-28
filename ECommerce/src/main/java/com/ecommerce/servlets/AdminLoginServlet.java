package com.ecommerce.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

@WebServlet("/adminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Database connection (assumes JDBC is properly set up)
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // JDBC setup (replace with your DB credentials)
            String url = "jdbc:mysql://localhost:3306/ecommerce";
            String dbUser = "root";
            String dbPassword = "Madhu@123";
            conn = DriverManager.getConnection(url, dbUser, dbPassword);

            // SQL query to check if admin exists
            String query = "SELECT * FROM admins WHERE username = ? AND password = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            // Check if user exists and redirect accordingly
            if (rs.next()) {
                // Successful login
                HttpSession session = request.getSession();
                session.setAttribute("admin", username);  // Store the admin in session
                response.sendRedirect("manageProducts.jsp"); // Redirect to manage products page
            } else {
                // Invalid credentials
                response.sendRedirect("admin.jsp?error=Invalid credentials");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("admin.jsp?error=Database error");
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
