package com.bcwellness.service;

import com.bcwellness.dao.StudentDAO;
import com.bcwellness.model.Student;
import com.bcwellness.util.PasswordUtil;
import java.util.ArrayList;

/* ── Concrete implementation of AuthService ── */
public class AuthServiceImpl implements AuthService {

    /* ── Fields ── */
    private final StudentDAO dao = new StudentDAO();                 // data-access helper
    private static final String NAME_RGX = "^(?=.*\\p{L}.*\\p{L})[\\p{L}'\\- ]+$";

    /* ── Login flow ── */
    @Override
    public Student login(String email, String plainPw) {
        Student s = dao.findByEmail(email);
        return (s != null && PasswordUtil.matches(plainPw, s.getPassword())) ? s : null;
    }

    /* ── Registration flow ── */
    @Override
    public ArrayList<String> register(Student stu) {
        ArrayList<String> errs = new ArrayList<>();

        /* validate basic syntax */
        if (stu.getStudentNumber() == null || !stu.getStudentNumber().matches("\\d{6}")) { errs.add("Student number must be exactly 6 digits."); }
        if (stu.getName() == null || !stu.getName().matches(NAME_RGX)) { errs.add("Invalid name (letters, -, ')."); }
        if (stu.getSurname() == null || !stu.getSurname().matches(NAME_RGX)) { errs.add("Invalid surname (letters, -, ')."); }
        if (stu.getEmail() == null || !stu.getEmail().matches("^[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) { errs.add("Bad email format."); }
        if (stu.getPhone() == null || !stu.getPhone().matches("^0[6-8][0-9]{8}$")) { errs.add("Phone must be SA mobile (0821234567)."); }
        if (!PasswordUtil.strong(stu.getPassword())) { errs.add("Weak password."); }

        /* uniqueness + insert */
        if (errs.isEmpty()) {
            try {
                if (dao.emailExists(stu.getEmail())) { errs.add("Email already exists."); }
                if (dao.studentNumberExists(stu.getStudentNumber())) { errs.add("Student already exists."); }

                if (errs.isEmpty()) {                        // finally write to DB
                    stu.setPassword(PasswordUtil.hash(stu.getPassword()));
                    dao.insertStudent(stu);
                }
            } catch (Exception ex) {
                errs.add("DB error; please retry.");
            }
        }
        return errs;
    }
}
