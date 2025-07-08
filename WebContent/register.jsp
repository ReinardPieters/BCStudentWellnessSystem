<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>BC Wellness System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #e0f7fa, #fce4ec);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: 'Segoe UI', sans-serif;
        }

        .card {
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0,0,0,0.1);
            background-color: white;
        }

        h1 {
            color: #333;
        }

        .btn {
            width: 120px;
        }
    </style>
</head>
<body class="bg-light d-flex justify-content-center align-items-center vh-100">
<div class="card text-center p-5 shadow">
    <img src="images/logo.png" alt="BC Wellness Logo" class="mx-auto mb-4" style="width: 150px;">

    <h1 class="mb-3">Create Your Wellness Account</h1>
    <p class="mb-4">Please fill in the details below</p>

    <form action="register" method="post">

        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <div class="mb-3 text-start">
            <label for="student_number" class="form-label">Student Number</label>
            <input type="text" class="form-control" id="student_number" name="student_number" required pattern="[0-9]+" title="Student number must be digits only">
        </div>

        <div class="mb-3 text-start">
            <label for="name" class="form-label">First Name</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>

        <div class="mb-3 text-start">
            <label for="surname" class="form-label">Surname</label>
            <input type="text" class="form-control" id="surname" name="surname" required>
        </div>

        <div class="mb-3 text-start">
            <label for="email" class="form-label">Email Address</label>
            <input type="email" class="form-control" id="email" name="email" required pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" title="Please enter a valid email like user@example.com">
        </div>

        <div class="mb-3 text-start">
            <label for="phone" class="form-label">Phone Number</label>
            <input type="tel" class="form-control" id="phone" name="phone" required pattern="0[6-8][0-9]{8}" title="Enter a valid South African number (e.g., 0821234567)">
        </div>

        <div class="mb-4 text-start">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>

        <button type="submit" class="btn btn-success w-100 mb-2">Register</button>
        <a href="index.jsp" class="btn btn-outline-secondary w-100">Back to Home</a>
    </form>
</div>
</body>
</html>
