package com.bcwellness.service;
import com.bcwellness.model.Student;

public interface AuthService {
    Student login(String email, String password);
    void register(Student student);
}
