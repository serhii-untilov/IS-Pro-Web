package ua.in.usv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

import ua.in.usv.entity.CustomUser;
import ua.in.usv.service.UserService;
import ua.in.usv.stay.PasswordBlock;


@RunWith(SpringRunner.class)
@SpringBootTest
public class D4ApplicationTests {

	@Autowired
	private UserService userService;


	@Test
	public void contextLoads() {
	}

	@Test
	public void passwordHashRead(){
		CustomUser user = userService.findByLogin("usv");
		PasswordBlock passwordBlock = new PasswordBlock(user.getUserPassword().getPasswordBlob());
		assertThat(passwordBlock.getHash().length == PasswordBlock.digest_len);
	}
}
