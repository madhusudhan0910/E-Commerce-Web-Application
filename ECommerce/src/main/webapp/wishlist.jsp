<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ecommerce.model.WishlistItem" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    HttpSession sessionObj = request.getSession();
    List<WishlistItem> wishlist = (List<WishlistItem>) sessionObj.getAttribute("wishlist");

    if (wishlist == null) {
        wishlist = new java.util.ArrayList<>();
        sessionObj.setAttribute("wishlist", wishlist);
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Wishlist</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: center; }
        th { background-color: #f4f4f4; }
        .empty { text-align: center; margin-top: 20px; font-size: 18px; color: gray; }
    </style>
</head>
<body>

    <h2>Your Wishlist</h2>

    <% if (wishlist.isEmpty()) { %>
        <p class="empty">Your wishlist is empty.</p>
    <% } else { %>
        <table>
            <thead>
                <tr>
                    <th>Image</th>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% for (WishlistItem item : wishlist) { %>
                    <tr>
                        <td><img src="images/<%= item.getImage() %>" width="50"></td>
                        <td><%= item.getName() %></td>
                        <td>$<%= item.getPrice() %></td>
                        <td>
                            <form action="WishlistServlet" method="post">
                                <input type="hidden" name="id" value="<%= item.getId() %>">
                                <input type="hidden" name="action" value="remove">
                                <button type="submit">Remove</button>
                            </form>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } %>

    <br>
    <a href="index.jsp">Continue Shopping</a>

</body>
</html>
