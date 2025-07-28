package com.ecommerce.servlet;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ShopServlet")
public class ShopServlet extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");

        // Get products by category (or all products if category is null)
        List<Product> productList;
        if (category != null && !category.isEmpty()) {
            productList = productDAO.getProductsByCategory(category);
        } else {
            productList = productDAO.getAllProducts();
        }

        // Set product list in request and forward to shop.jsp
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("shop.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Product> cart = (List<Product>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String category = request.getParameter("category");
        String image = request.getParameter("image");

        // Add selected product to cart
        Product product = new Product(id, name, price, category, image);
        cart.add(product);

        session.setAttribute("cart", cart);
        response.sendRedirect("shop.jsp");  // Redirect back to shop page
    }
}
