<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, com.ecommerce.model.Product" %>
<%@ page import="com.ecommerce.dao.ProductDAO" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Products - Admin Panel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<jsp:include page="navbar.jsp"/>

<div class="container mt-5">
    <h2 class="text-center">Manage Products</h2>

    <!-- Add Product Form -->
    <form action="ProductServlet" method="post" class="mb-4">
        <div class="mb-3">
            <label class="form-label">Product Name</label>
            <input type="text" name="name" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Price</label>
            <input type="number" name="price" step="0.01" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Category</label>
            <input type="text" name="category" class="form-control" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Image URL</label>
            <input type="text" name="image" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary w-100">Add Product</button>
    </form>

    <!-- Display Products -->
    <h3>Product List</h3>
    <table class="table table-bordered">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Category</th>
            <th>Image</th>
            <th>Action</th>
        </tr>
        <%
            ProductDAO productDAO = new ProductDAO();
            List<Product> productList = productDAO.getAllProducts();
            for (Product product : productList) {
        %>
        <tr>
            <td><%= product.getId() %></td>
            <td><%= product.getName() %></td>
            <td>$<%= product.getPrice() %></td>
            <td><%= product.getCategory() %></td>
            <td><img src="images/<%= product.getImage() %>" width="50"></td>
            <td>
                <a href="ProductServlet?action=delete&id=<%= product.getId() %>" class="btn btn-danger btn-sm">Delete</a>
            </td>
        </tr>
        <% } %>
    </table>
</div>

<footer class="bg-dark text-white text-center py-4 mt-5">
    <p>&copy; 2025 E-Commerce. All rights reserved.</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
