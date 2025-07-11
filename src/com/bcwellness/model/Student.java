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

    /* getters (add as needed) */
    public String getEmail()   { return email; }
    public String getPassword(){ return password; }
}
