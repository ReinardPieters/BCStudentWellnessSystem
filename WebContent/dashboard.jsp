<%@ page import="com.bcwellness.model.Student" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Student student = (Student) session.getAttribute("student");
    System.out.println(student);
    if (student == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>BC Wellness System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #e0f7fa, #fce4ec);
            height: 100vh;
            font-family: 'Segoe UI', sans-serif;
        }
        nav {
            background-color: #37474f;
            color: white;
            padding: 10px 20px;
            border-bottom-left-radius: 15px;
            border-bottom-right-radius: 15px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.25);
            margin: 0 15px;
        }
        .navbar .navbar-brand,
        .navbar a {
            color: white;
        }
        .navbar a:hover {
            color: #eceff1;
        }

        h1 {
            color: #333;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg shadow-sm">
    <div class="container-fluid">
        <!-- Left Side: Student Info -->
        <span class="navbar-brand mb-0 h5">
            Welcome, ${sessionScope.name} (${sessionScope.studentNumber})
        </span>

        <!-- Right Side: Logout -->
        <div class="d-flex">
            <a href="logout" class="btn btn-outline-light">Logout</a>
        </div>
    </div>
</nav>

<!--Dashboard Content -->
<div class="container mt-5">
    <h2 class="mb-4">Stuff will go here</h2>
</div>

</body>
</html>
