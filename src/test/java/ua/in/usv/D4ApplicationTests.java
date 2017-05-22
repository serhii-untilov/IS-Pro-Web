package ua.in.usv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.in.usv.stay.Block;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class D4ApplicationTests {

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
	}
}
