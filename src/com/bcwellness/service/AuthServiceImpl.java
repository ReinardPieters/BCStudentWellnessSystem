package com.bcwellness.service;

import com.bcwellness.dao.StudentDAO;
import com.bcwellness.model.Student;
import com.bcwellness.util.PasswordUtil;

public class AuthServiceImpl implements AuthService {

    private final StudentDAO dao = new StudentDAO();

    @Override
    public Student login(String email, String plainPw) {
        Student s = dao.findByEmail(email);
        if (s != null && PasswordUtil.matches(plainPw, s.getPassword())) {
            return s;
        }
        return null;
    }

    @Override
    public void register(Student s) {

    }
}
