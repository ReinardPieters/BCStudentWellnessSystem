# BC Student Wellness Management System

A two-part academic project for PRG3781 (Programming 3781) at Belgium Campus, designed to demonstrate proficiency in Java programming, database integration, GUI development, and web technologies.

---

## 🧩 Project Structure

### ✅ Milestone 1: Web Application (JSP + PostgreSQL)

A web-based application enabling students to register and log in to access wellness services.

#### 🔹 Features
- Student Registration & Login (JSP + Servlet)
- PostgreSQL Database Integration
- Session Management with HttpSession
- Password Hashing
- Input Validation & User Feedback
- Clean Navigation via `index.jsp`

#### 🗃️ Database Schema

**Users Table**
| Column         | Type      | Constraints         |
|----------------|-----------|---------------------|
| student_number | VARCHAR   | PRIMARY KEY, UNIQUE |
| name           | VARCHAR   | NOT NULL            |
| surname        | VARCHAR   | NOT NULL            |
| email          | VARCHAR   | UNIQUE, NOT NULL    |
| phone          | VARCHAR   | NOT NULL            |
| password       | VARCHAR   | NOT NULL (hashed)   |

#### 🧪 Functional Checklist
- Registration with duplicate checks
- Login with proper credential validation
- Session-based access control
- Logout with session invalidation

---
### 🚀 Running the Applications

### 📌 Milestone 1 (Web App)
1. Ensure PostgreSQL is running and the `users` table exists.
2. Deploy to Apache Tomcat.
3. Visit `http://localhost:8080/index.jsp`
---

## 🛡️ Academic Integrity

This project adheres strictly to Belgium Campus academic policies. All contributions are original and reflect the learning outcomes of all participating members.