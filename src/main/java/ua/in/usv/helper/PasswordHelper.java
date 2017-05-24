package ua.in.usv.helper;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class PasswordHelper {

    public static String encode(String password) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String passHash = encoder.encodePassword(password, null);
        return passHash;
    }
}
