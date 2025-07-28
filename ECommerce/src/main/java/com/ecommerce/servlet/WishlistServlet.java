package com.ecommerce.servlet;

import com.ecommerce.model.WishlistItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/WishlistServlet")
public class WishlistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.trim().isEmpty()) {
            response.sendRedirect("wishlist.jsp?error=Invalid action");
            return;
        }

        HttpSession session = request.getSession();
        List<WishlistItem> wishlist = (List<WishlistItem>) session.getAttribute("wishlist");

        if (wishlist == null) {
            wishlist = new ArrayList<>();
            session.setAttribute("wishlist", wishlist);
        }

        boolean success = false;

        switch (action) {
            case "add":
                success = addToWishlist(request, wishlist);
                break;
            case "remove":
                success = removeFromWishlist(request, wishlist);
                break;
            default:
                response.sendRedirect("wishlist.jsp?error=Invalid action");
                return;
        }

        if (!success) {
            response.sendRedirect("wishlist.jsp?error=Action failed");
        } else {
            session.setAttribute("wishlist", wishlist);
            response.sendRedirect("wishlist.jsp");
        }
    }

    private boolean addToWishlist(HttpServletRequest request, List<WishlistItem> wishlist) {
        try {
            String idParam = request.getParameter("id");
            String name = request.getParameter("name");
            String priceParam = request.getParameter("price");
            String image = request.getParameter("image");

            if (idParam == null || idParam.trim().isEmpty() ||
                name == null || name.trim().isEmpty() ||
                priceParam == null || priceParam.trim().isEmpty() ||
                image == null || image.trim().isEmpty()) {
                return false;
            }

            int id = Integer.parseInt(idParam.trim());
            double price = Double.parseDouble(priceParam.trim());

            for (WishlistItem item : wishlist) {
                if (item.getId() == id) {
                    return false; // Item already in wishlist
                }
            }

            wishlist.add(new WishlistItem(id, name, price, image));
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean removeFromWishlist(HttpServletRequest request, List<WishlistItem> wishlist) {
        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.trim().isEmpty()) {
                return false;
            }
            int id = Integer.parseInt(idParam.trim());
            return wishlist.removeIf(item -> item.getId() == id);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
