package ua.in.usv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static junit.framework.TestCase.assertTrue;

import ua.in.usv.service.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Test
	public void contextLoads() {
	}
}
