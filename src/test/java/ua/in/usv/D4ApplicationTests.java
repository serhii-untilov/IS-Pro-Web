package ua.in.usv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

import ua.in.usv.entity.CustomUser;
import ua.in.usv.repository.UserRepository;
import ua.in.usv.service.UserService;
import ua.in.usv.stay.Block;


@RunWith(SpringRunner.class)
@SpringBootTest
public class D4ApplicationTests {

	@Autowired
	private UserService userService;


	@Test
	public void contextLoads() {
	}

	@Test
	public void stayBlockTest() {
		Block block = new Block("test");
		assertThat(block.getSize()).isEqualTo(6);
		byte[] header = block.getHeader();
		assertThat(header.length).isEqualTo(2);
		byte[] body = block.getBody();
		assertThat(body.length).isEqualTo(4);
		assertTrue(block.toString().equals("test"));
	}

	@Test
	public void readUserPassword(){
		CustomUser user = userService.findByLogin("usv");
		String passwordHash = user.getPasswordHash();
		assertTrue(passwordHash.equals(""));
	}
}
