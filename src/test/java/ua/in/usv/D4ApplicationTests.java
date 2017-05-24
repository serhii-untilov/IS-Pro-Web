package ua.in.usv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

import ua.in.usv.entity.CustomUser;
import ua.in.usv.service.UserService;
import ua.in.usv.stay.PasswordBlock;


@RunWith(SpringRunner.class)
@SpringBootTest
public class D4ApplicationTests {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;


	@Test
	public void contextLoads() {
	}

	@Test
	public void passwordHashRead(){
		CustomUser user = userService.findByLogin("usv");
		PasswordBlock passwordBlock = new PasswordBlock(user.getUserPassword().getPasswordBlob());
		assertTrue(passwordBlock.getHash().length == PasswordBlock.digest_len);
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
}
