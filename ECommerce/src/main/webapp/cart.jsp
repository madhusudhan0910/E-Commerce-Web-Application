<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, com.ecommerce.model.CartItem" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="container mt-5">
    <h2 class="text-center">Your Shopping Cart</h2>

    <%
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        double grandTotal = 0.0;
        if (cart != null && !cart.isEmpty()) {
    %>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Image</th>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (CartItem item : cart) {
                    double total = item.getPrice() * item.getQuantity();
                    grandTotal += total;
            %>
            <tr>
                <td><img src="images/<%= item.getImage() %>" width="50" alt="Product"></td>
                <td><%= item.getName() %></td>
                <td>$<%= item.getPrice() %></td>
                <td><%= item.getQuantity() %></td>
                <td>$<%= total %></td>
                <td>
                    <a href="CartServlet?action=remove&id=<%= item.getId() %>" class="btn btn-danger">Remove</a>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
    <h4>Total: $<%= grandTotal %></h4>
    
    <!-- 🛒 Checkout Button -->
    <form action="CheckoutServlet" method="post">
        <button type="submit" class="btn btn-success">Proceed to Checkout</button>
    </form>

    <% } else { %>
        <p class="text-center">Your cart is empty!</p>
    <% } %>
</div>

</body>
</html>
