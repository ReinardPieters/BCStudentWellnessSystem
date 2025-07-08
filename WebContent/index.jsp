<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        img{
            border-radius: 15px;
        }
        .btn {
            width: 120px;
        }
    </style>
</head>
<body class="bg-light d-flex justify-content-center align-items-center vh-100">
<div class="card text-center p-5 shadow">
    <img src="images/logo.png" alt="BC Wellness Logo" class="mx-auto mb-4" style="width: 300px;">

    <h1 class="mb-3">Welcome to the BC Student Wellness System</h1>
    <p class="mb-4">Take a step toward a healthier academic journey.</p>

    <div>
        <a href="login.jsp" class="btn btn-primary btn-lg me-3">Login</a>
        <a href="register.jsp" class="btn btn-success btn-lg">Register</a>
    </div>
</div>
</body>
</html>
