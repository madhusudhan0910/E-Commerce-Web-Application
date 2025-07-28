package com.ecommerce.servlet;

import com.ecommerce.dao.DBConnection;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/RegisterServlet") 
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username"); // Remove if your DB has no username
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            response.sendRedirect("register.jsp?error=All fields are required");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            // Check if email already exists
            String checkEmailQuery = "SELECT COUNT(*) FROM users WHERE email = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkEmailQuery)) {
                checkStmt.setString(1, email);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        response.sendRedirect("register.jsp?error=Email already exists");
                        return;
                    }
                }
            }

            // Insert user into the database
            String insertQuery = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)"; 
            try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                stmt.setString(1, username); // Remove if `username` is not in DB
                stmt.setString(2, email);
                stmt.setString(3, password);  // Consider hashing the password

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    response.sendRedirect("register-success.jsp");
                } else {
                    response.sendRedirect("register.jsp?error=Registration failed. Try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp?error=Database error occurred.");
        }
    }
}
