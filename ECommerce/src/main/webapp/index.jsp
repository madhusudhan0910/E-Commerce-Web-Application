<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome - E-Commerce</title>

    <!-- Link to External CSS -->
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!-- Navbar -->
<jsp:include page="navbar.jsp"/>

<!-- Hero Section -->
<div class="container text-center mt-5">
    <h1 class="display-4">Welcome to Our Online Shop</h1>
    <p class="lead">Explore a wide range of products and enjoy a seamless shopping experience with us.</p>
    <a href="shop.jsp" class="btn btn-primary btn-lg">Shop Now</a>
</div>

<!-- About Section -->
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6">
            <h2>About Our Store</h2>
            <p>We offer a variety of products ranging from electronics to fashion. Our mission is to provide quality items at affordable prices.</p>
            <a href="shop.jsp" class="btn btn-primary">Browse Our Collection</a>
        </div>
        <div class="col-md-6">
            <img src="images/store_image.jpg" class="img-fluid rounded" alt="Store Image">
        </div>
    </div>
</div>

<!-- Link to Admin Dashboard -->
<div class="container mt-5">
    <h2 class="text-center">Admin Section</h2>
    <p class="text-center">
        <a href="admin.jsp" class="btn btn-info">Go to Admin Dashboard</a>
    </p>
</div>

<!-- Footer -->
<footer class="bg-dark text-white text-center py-4 mt-5">
    <p>&copy; 2025 E-Commerce. All rights reserved.</p>
</footer>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
