package com.bcwellness.service;

import com.bcwellness.dao.StudentDAO;
import com.bcwellness.model.Student;
import com.bcwellness.util.PasswordUtil;

import java.util.ArrayList;

public class AuthServiceImpl implements AuthService {

    private final StudentDAO dao = new StudentDAO();
    private static final String NAME_REGEX = "^(?=.*\\p{L}.*\\p{L})[\\p{L}'\\- ]+$";


    @Override
    public Student login(String email, String plainPw) {
        Student s = dao.findByEmail(email);
        if (s != null && PasswordUtil.matches(plainPw, s.getPassword())) {
            return s;
        }
        return null;
    }

    @Override
    public ArrayList<String> register(Student stu) {
        ArrayList<String> errors = new ArrayList<>();

        String sn = stu.getStudentNumber();
        String n = stu.getName();
        String s = stu.getSurname();
        String e = stu.getEmail();
        String p = stu.getPhone();
        String pw = stu.getPassword();

        if (sn == null || !sn.matches("\\d{6}")) errors.add("Student number must be exactly 6 digits.");
        if (n == null || !n.matches(NAME_REGEX)) errors.add("Invalid name. Use letters only (min 2 characters).");
        if (s == null || !s.matches(NAME_REGEX)) errors.add("Invalid surname. Use letters only (min 2 characters).");
        if (e == null || !e.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) errors.add("Invalid email address format.");
        if (p == null || !p.matches("^0[6-8][0-9]{8}$")) errors.add("Phone number must be a valid SA mobile number (e.g. 0821234567).");
        if (!PasswordUtil.strong(pw)) errors.add("Password must be at least 8 characters and include uppercase, lowercase, number, and symbol.");

        if (errors.isEmpty()) {
            try {
                var emailExists = dao.emailExists(e);
                var studentExists = dao.studentNumberExists(sn);
                if (emailExists) errors.add("Email already exists.");
                if (studentExists) errors.add("Student already exists.");

                if (errors.isEmpty()) {
                    var pwHash = PasswordUtil.hash(pw);
                    stu.setPassword(pwHash);
                    dao.insertStudent(stu);
                }
            } catch (Exception ex) {
                errors.add("Internal database error, please try again.");
            }
        }
        return errors;
    }
}
