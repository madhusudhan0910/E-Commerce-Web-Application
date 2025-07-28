package com.ecommerce.servlet;

import com.ecommerce.dao.DBConnection;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("id"));
            try (Connection conn = DBConnection.getConnection()) {
                String query = "DELETE FROM products WHERE id=?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setInt(1, productId);
                    stmt.executeUpdate();
                    response.sendRedirect("admin.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
