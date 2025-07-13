package com.bcwellness.service;
import com.bcwellness.model.Student;

import java.util.ArrayList;

public interface AuthService {
    Student login(String email, String password);
    ArrayList<String> register(Student student);
}
