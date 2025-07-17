package com.bcwellness.util;

import org.mindrot.jbcrypt.BCrypt;

/*  ── Password Helpers ────────────────────────────────────────────────
    • Pure-utility (static) class – no state, no constructor.
    • Uses BCrypt for hashing / verifying user passwords.
    • Also provides a basic “strong-password” rule checker.
   ──────────────────────────────────────────────────────────────────── */
public final class PasswordUtil {

    /* ── BCrypt hash ── */
    public static String hash(String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt());
    }

    /* ── BCrypt verify ── */
    public static boolean matches(String plain, String storedHash) {
        return BCrypt.checkpw(plain, storedHash);
    }

    /* ── Strength check (≥8 chars, A-Z, a-z, 0-9, symbol) ── */
    public static boolean strong(String plain) {
        return  plain != null
                && plain.length() >= 8
                && plain.matches(".*[A-Z].*")          // upper
                && plain.matches(".*[a-z].*")          // lower
                && plain.matches(".*\\d.*")            // digit
                && plain.matches(".*[^A-Za-z0-9].*");  // symbol
    }
}
