package ua.in.usv.helper;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class PasswordHelper {

    public static String encode(String password) {
        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        String passHash = encoder.encodePassword(password, null);
        return passHash;
    }
}
