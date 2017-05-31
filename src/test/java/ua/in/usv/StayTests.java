package ua.in.usv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.in.usv.entity.root.CustomUser;
import ua.in.usv.helper.Byte2String;
import ua.in.usv.service.UserService;
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
    public void md5PasswordHashCompareIS() {
        CustomUser user = userService.findByLogin("usv");
        PasswordBlock passwordBlock = new PasswordBlock(user.getUserPassword().getPasswordBlob());
        String hashFromBase = passwordBlock.getPasswordHash();

        Md5HashEncoder md = new Md5HashEncoder();
        long salt = passwordBlock.getSalt();
        long key = user.getId();
        String password = "";
        byte[] output = new byte[Md5HashEncoder.digest_len];
        md.generateHash(key, password, salt, output);
        String hashFromPass = Byte2String.encode(output);

        assertTrue(hashFromBase.equals(hashFromPass));
    }
}
