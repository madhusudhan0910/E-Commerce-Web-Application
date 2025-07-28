package com.ecommerce.model;

public class Product {
    private int id;
    private String name;
    private double price;
    private String category;
    private String image;

    // Default constructor
    public Product() {
        this.id = 0;
        this.name = "";
        this.price = 0.0;
        this.category = "";
        this.image = "";
    }

    // Constructor for retrieving existing products
    public Product(int id, String name, double price, String category, String image) {
        this.id = id;
        this.name = name;
        this.price = Math.max(price, 0.0);  // Ensuring no negative price
        this.category = category;
        this.image = image;
    }

    // Constructor for adding a new product (without ID)
    public Product(String name, double price, String category, String image) {
        this.name = name;
        this.price = Math.max(price, 0.0);
        this.category = category;
        this.image = image;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { 
        this.price = Math.max(price, 0.0);  // Ensuring price is non-negative
    }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    // Overriding toString() for debugging
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
