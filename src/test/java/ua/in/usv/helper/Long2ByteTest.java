package ua.in.usv.helper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Long2ByteTest {

    @Test
    public void encodeTest(){

        long toEncode = 1234567890;
        byte[] byteArray = Long2Byte.encode(toEncode);
        long encoded = Long2Byte.decode(byteArray, 0);
        assertTrue(encoded == toEncode);
    }

    @Test
    public void decodeTest(){
        byte[] toDecode = {1,2,3,4};
        long decoded = Long2Byte.decode(toDecode, 0);
        byte[] encoded = Long2Byte.encode(decoded);
        assertTrue(Arrays.equals(encoded, toDecode));
    }
}