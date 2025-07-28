# 🛒 E-Commerce Web Application

This is a full-stack Java-based E-Commerce Web Application built using **JSP**, **Servlets**, **JDBC**, and **MySQL**. The app allows users to browse products, add to cart, and place orders. Admins can manage the product catalog.

---

## 📁 Project Structure

```
ECommerceProject/
├── src/
│   ├── com.ecommerce.dao/
│   │   ├── DBConnection.java
│   │   ├── UserDAO.java
│   │   ├── ProductDAO.java
│   │   ├── OrderDAO.java
│   ├── com.ecommerce.model/
│   │   ├── User.java
│   │   ├── Product.java
│   │   ├── Order.java
│   ├── com.ecommerce.servlet/
│   │   ├── RegisterServlet.java
│   │   ├── LoginServlet.java
│   │   ├── LogoutServlet.java
│   │   ├── ProductServlet.java
│   │   ├── CartServlet.java
│   │   ├── CheckoutServlet.java
├── WebContent/
│   ├── index.jsp
│   ├── login.jsp
│   ├── register.jsp
│   ├── products.jsp
│   ├── cart.jsp
│   ├── checkout.jsp
│   ├── admin.jsp
│   ├── css/
│   │   ├── styles.css
│   ├── WEB-INF/
│   │   ├── web.xml
├── lib/
│   ├── mysql-connector-java-8.0.26.jar
└── README.md
```

---

## ✅ Features

- 👥 User Registration and Login
- 🛍️ Browse Products
- 🛒 Add to Cart and Checkout
- 🧾 Order Management
- 🧑‍💼 Admin Panel for managing products
- 🎨 Clean UI with HTML/CSS

---

## 🛠️ Tech Stack

- Java (JSP, Servlets)
- JDBC
- MySQL
- HTML/CSS (Bootstrap optional)
- Apache Tomcat 9+
- Eclipse or IntelliJ IDEA

---

## 🧱 Database Setup

1. Create a new database in MySQL:
```sql
CREATE DATABASE ecommerce;
USE ecommerce;
```

2. Create the required tables:
```sql
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100) UNIQUE,
  password VARCHAR(100),
  role VARCHAR(10) DEFAULT 'user'
);

CREATE TABLE products (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  description TEXT,
  price DECIMAL(10, 2),
  image VARCHAR(255),
  stock INT
);

CREATE TABLE orders (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT,
  product_id INT,
  quantity INT,
  order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (product_id) REFERENCES products(id)
);
```

---

## 🔐 DB Credentials

In `DBConnection.java`, set:

```java
String url = "jdbc:mysql://localhost:3306/ecommerce";
String username = "root";
String password = "Madhu@123";
```

Also ensure `mysql-connector-java-8.0.26.jar` is in your `/lib` and added to build path.

---

## 🧪 How to Run Locally

1. Clone this repo:
```bash
git clone https://github.com/YOUR_USERNAME/ECommerceProject.git
```

2. Import into **Eclipse** as a **Dynamic Web Project**.

3. Configure **Apache Tomcat** in Eclipse.

4. Start the server and open in browser:
```
http://localhost:8080/ECommerceProject/
```




---

## 📸 Screenshots (Optional)

You can include screenshots like:
- Home Page
- Product Page
- Admin Panel
- Cart View
- Checkout Confirmation

---

## 🤝 Contributing

Pull requests are welcome. For major changes, please open an issue first.

---

## 📜 License

This project is licensed under the MIT License.
