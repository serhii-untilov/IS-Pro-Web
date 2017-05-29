package ua.in.usv.stay;

import org.springframework.security.authentication.encoding.PasswordEncoder;
import ua.in.usv.helper.ByteArrayConvert;

public class IsproPasswordEncoder implements PasswordEncoder {

    @Override
    public String encodePassword(String rawPass, Object salt) {
        return null;
    }

    @Override
    public boolean isPasswordValid(String encPass, String rawPass, Object saltObj) {

        Md5HashEncoder md5HashEncoder = new Md5HashEncoder();

        long salt = ((Number)saltObj).longValue();
        byte[] output = new byte[Md5HashEncoder.digest_len];
        md5HashEncoder.generateHash(43,rawPass, salt, output);
        String hashFromPass = ByteArrayConvert.toString(output);

        return false;
    }
}
