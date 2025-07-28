package com.ecommerce.dao;

import com.ecommerce.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WishlistDAO {
    public void addToWishlist(int userId, int productId) {
        String sql = "INSERT INTO wishlist (user_id, product_id) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeFromWishlist(int userId, int productId) {
        String sql = "DELETE FROM wishlist WHERE user_id = ? AND product_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getWishlist(int userId) {
        List<Product> wishlist = new ArrayList<>();
        String sql = "SELECT p.* FROM products p JOIN wishlist w ON p.id = w.product_id WHERE w.user_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                wishlist.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("category"),
                        rs.getString("image")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wishlist;
    }
}
