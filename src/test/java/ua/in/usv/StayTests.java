package ua.in.usv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ua.in.usv.entity.CustomUser;
import ua.in.usv.helper.ByteArrayConvert;
import ua.in.usv.service.UserService;
import ua.in.usv.stay.GostHashEncoder;
import ua.in.usv.stay.PasswordBlock;
import ua.in.usv.stay.Md5HashEncoder;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StayTests {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Test
    public void passwordHashRead(){
        CustomUser user = userService.findByLogin("usv");
        PasswordBlock passwordBlock = new PasswordBlock(user.getUserPassword().getPasswordBlob());
        assertTrue(passwordBlock.getHash().length == Md5HashEncoder.digest_len);
    }

    @Test
    public void md5PasswordHashCompare(){
        CustomUser user = userService.findByLogin("usv");
        PasswordBlock passwordBlock = new PasswordBlock(user.getUserPassword().getPasswordBlob());
        Md5PasswordEncoder md = new Md5PasswordEncoder();
        Object salt = passwordBlock.getSalt();
        String pass = md.encodePassword("", salt);
        assertTrue(md.isPasswordValid(passwordBlock.toString(), "", salt));
    }

    @Test
    public void md5PasswordHashCompareIS() {
        CustomUser user = userService.findByLogin("usv");
        PasswordBlock passwordBlock = new PasswordBlock(user.getUserPassword().getPasswordBlob());
        String hashFromBase = passwordBlock.toString();

        Md5HashEncoder md = new Md5HashEncoder();
        long salt = passwordBlock.getSalt();
        byte[] output = new byte[Md5HashEncoder.digest_len];
        md.generateHash("", salt, output);
        String hashFromPass = ByteArrayConvert.toString(output);

        assertTrue(hashFromBase.equals(hashFromPass));
    }

    @Test
    public void gostPasswordHashCompareIS() {
        CustomUser user = userService.findByLogin("usv");
        PasswordBlock passwordBlock = new PasswordBlock(user.getUserPassword().getPasswordBlob());
        String hashFromBase = passwordBlock.toString();

        GostHashEncoder md = new GostHashEncoder();
        long salt = passwordBlock.getSalt();
        byte[] output = new byte[Md5HashEncoder.digest_len];
        md.Encode(byte[] key, int key_len, byte[] input, int input_len, byte[] output) {

                ()("", salt, output);
        String hashFromPass = ByteArrayConvert.toString(output);

        assertTrue(hashFromBase.equals(hashFromPass));
    }
}
