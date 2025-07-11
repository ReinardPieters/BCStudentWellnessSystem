package com.bcwellness.util;

import org.mindrot.jbcrypt.BCrypt;

/** Stateless helpers for hashing and verification. */
public final class PasswordUtil {

    public static String hash(String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt());
    }

    public static boolean matches(String plain, String storedHash) {
        return BCrypt.checkpw(plain, storedHash);
    }
}
