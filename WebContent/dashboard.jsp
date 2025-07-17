<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.bcwellness.model.Student" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- ── Guard: redirect to login if no student in session ──────────── --%>
<%
    Student student = (Student) session.getAttribute("student");
    if (student == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>BC Wellness Dashboard</title>

    <!--  Bootstrap + Icons  -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">

    <style>
        /* ==========  SIMPLE INLINE DASHBOARD STYLES  ========== */
        body{
            background:linear-gradient(135deg,#e0f7fa,#fce4ec);
            min-height:100vh;
            font-family:"Segoe UI",sans-serif;
        }
        .topbar{
            background:#37474f;
            color:#fff;
            padding:.75rem 1.25rem;
            border-bottom-left-radius:15px;
            border-bottom-right-radius:15px;
            box-shadow:0 4px 12px rgba(0,0,0,.25)
        }
        .profile-thumb{
            width:2.6rem;height:2.6rem;
            border-radius:50%;
            object-fit:cover;
            border:2px solid #fff;
        }
        .action-tile{
            border:2px solid #e0e0e0;
            border-radius:12px;
            min-height:90px;
            display:flex;flex-direction:column;
            justify-content:center;
            align-items:center;
            transition:transform .15s,box-shadow .15s;
            background:#fff;
        }
        .action-tile:hover{
            transform:translateY(-3px);
            box-shadow:0 6px 14px rgba(0,0,0,.12);
        }
        .stat-card{
            border:2px solid #e0e0e0;
            border-radius:12px;
            padding:1rem 1.25rem;
            background:#fff;
        }
        .stat-card h6{font-size:.95rem;font-weight:700;margin-bottom:.25rem}
        .stat-card p{font-size:.85rem;margin-bottom:0;color:#555}
    </style>
</head>
<body>

<!-- ── Top navigation bar ─────────────────────────────────────────── -->
<nav class="topbar d-flex justify-content-between align-items-center">
    <span class="fw-semibold fs-5">BC Wellness Dashboard</span>

    <div class="d-flex align-items-center gap-3">
        <!-- envelope with unread badge -->
        <a href="messages.jsp" class="text-decoration-none text-white position-relative me-2">
            <i class="bi bi-envelope-fill fs-5"></i>
            <c:if test="${sessionScope.unread gt 0}">
                <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                    <c:out value="${sessionScope.unread}"/>
                </span>
            </c:if>
        </a>

        <!-- student name -->
        <span class="text-white me-2">
            <c:out value="${sessionScope.student.name} ${sessionScope.student.surname} (${sessionScope.student.studentNumber})"/>
        </span>

        <!-- auto-generated avatar -->
        <img src="https://api.dicebear.com/8.x/bottts-neutral/svg?seed=${sessionScope.student.studentNumber}"
             class="profile-thumb" alt="avatar"/>

        <a href="logout" class="btn btn-outline-light btn-sm ms-2">Logout</a>
    </div>
</nav>

<!-- ── Dashboard content ──────────────────────────────────────────── -->
<div class="container py-5">

    <!-- Quick-action tiles -->
    <div class="row g-4 justify-content-center mb-4">
        <div class="col-12 col-sm-6 col-lg-3">
            <a href="book.jsp" class="action-tile text-decoration-none text-dark">
                <i class="bi bi-calendar-plus fs-4 mb-1"></i>
                <span>Book Appointment</span>
            </a>
        </div>
        <div class="col-12 col-sm-6 col-lg-3">
            <a href="counselors.jsp" class="action-tile text-decoration-none text-dark">
                <i class="bi bi-people fs-4 mb-1"></i>
                <span>Counselors</span>
            </a>
        </div>
        <div class="col-12 col-sm-6 col-lg-3">
            <a href="feedback.jsp" class="action-tile text-decoration-none text-dark">
                <i class="bi bi-chat-left-text fs-4 mb-1"></i>
                <span>Feedback</span>
            </a>
        </div>
        <div class="col-12 col-sm-6 col-lg-3">
            <a href="resources.jsp" class="action-tile text-decoration-none text-dark">
                <i class="bi bi-journal-text fs-4 mb-1"></i>
                <span>Resources</span>
            </a>
        </div>
    </div>

    <!-- Stats row (currently dummy) -->
    <div class="row g-4">
        <div class="col-12 col-lg-4">
            <div class="stat-card">
                <h6><i class="bi bi-calendar-event me-1"></i> Next Appointment</h6>
                <p>Thu, 25 Jul 2025 · 10:00 · Dr Smith</p>
            </div>
        </div>
        <div class="col-12 col-lg-4">
            <div class="stat-card">
                <h6><i class="bi bi-emoji-smile me-1"></i> Mood Logs</h6>
                <p>3 entries this month</p>
            </div>
        </div>
        <div class="col-12 col-lg-4">
            <div class="stat-card">
                <h6><i class="bi bi-heart-pulse me-1"></i> Wellness Streak</h6>
                <p>7 days in a row — keep going!</p>
            </div>
        </div>
    </div>

</div>
</body>
</html>
