package com.ecommerce.servlet;

import com.ecommerce.model.CartItem;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        if ("add".equals(action)) {
            // Fetch product details from request
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String image = request.getParameter("image"); // Ensure image is passed

            // Check if product exists in cart
            boolean exists = false;
            for (CartItem item : cart) {
                if (item.getId() == id) {
                    item.setQuantity(item.getQuantity() + quantity);
                    exists = true;
                    break;
                }
            }

            // Add new item if it does not exist
            if (!exists) {
                cart.add(new CartItem(id, name, price, quantity, image));
            }

            session.setAttribute("cart", cart);
            response.sendRedirect("cart.jsp"); // Redirect after updating cart
        }
    }

    // ✅ Handle GET requests to display cart
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}
