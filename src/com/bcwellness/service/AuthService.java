package com.bcwellness.service;

import com.bcwellness.model.Student;
import java.util.ArrayList;

/* ── Contract for auth-related features ── */
public interface AuthService {

    /* ── Verify credentials; null ⇒ fail ── */
    Student login(String email, String password);

    /* ── Attempt registration; empty list ⇒ success ── */
    ArrayList<String> register(Student student);
}
