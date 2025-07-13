package com.bcwellness.model;

public class Student {
    private String studentNumber;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;        // will actually hold the stored hash

    public Student(String studentNumber, String name, String surname, String email, String phone, String password) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    /* secondary ctor for login-only look-ups */
    public Student(String email, String passwordHash) {
        this("", "", "", email, "", passwordHash);
    }

    /* getters */
    public String getEmail()            { return email; }
    public String getPassword()         { return password; }
    public String getStudentNumber()    { return studentNumber; }
    public String getName()             { return name; }
    public String getSurname()          { return surname; }
    public String getPhone()            { return phone; } // Hashed Password

    /* setters */
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setStudentNumber(String studentNumber) { this.studentNumber = studentNumber; }
    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setPhone(String phone) { this.phone = phone; }
}
