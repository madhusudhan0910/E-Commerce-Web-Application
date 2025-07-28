package com.ecommerce.servlet;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            productDAO.deleteProduct(id);
            response.sendRedirect("manageProducts.jsp");
            return;
        }

        // Default: Fetch and display all products
        List<Product> productList = productDAO.getAllProducts();
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("manageProducts.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String category = request.getParameter("category");
        String image = request.getParameter("image");

        Product product = new Product(0, name, price, category, image);
        productDAO.addProduct(product);
        response.sendRedirect("manageProducts.jsp");
    }
}
