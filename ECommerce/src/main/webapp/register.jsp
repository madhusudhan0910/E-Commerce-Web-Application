<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Register - E-Commerce</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card mx-auto shadow" style="max-width: 400px;">
        <div class="card-body">
            <h3 class="text-center text-success">Register</h3>
            
            <!-- Display error message -->
            <% String error = request.getParameter("error");
               if (error != null) { %>
                <div class="alert alert-danger text-center">
                    <%= error %>
                </div>
            <% } %>

            <form action="RegisterServlet" method="post">
                <div class="mb-3">
                    <label class="form-label">Username</label>
                    <input type="text" name="username" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" name="email" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Password</label>
                    <input type="password" name="password" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-success w-100">Register</button>
                <p class="mt-2 text-center">Already have an account? <a href="login.jsp">Login</a></p>
            </form>
        </div>
    </div>
</div>

</body>
</html>
