/**
 * IS-Pro TSysPuBlock implementation
 */
package ua.in.usv.stay;

import lombok.Getter;
import lombok.Setter;
import ua.in.usv.helper.Byte2String;
import ua.in.usv.helper.Long2Byte;

import java.util.Arrays;

@Getter
@Setter
public class PasswordBlock {

    private byte version;
    private byte format;
    private short unused1;
    private long salt;
    private byte[] hash;

    public PasswordBlock(byte[] block) {
        version = block[0];
        format = block[1];
        unused1 = 0;

//        salt  = (long)(block[7] & 0xFF) << 24;
//        salt |= (long)(block[6] & 0xFF) << 16;
//        salt |= (long)(block[5] & 0xFF) << 8;
//        salt |= (long)(block[4] & 0xFF);
        salt = Long2Byte.decode(block, 4);

        byte[] hash = new byte[block.length - 8];
        System.arraycopy(block, 8, hash, 0, block.length - 8);
        this.hash = hash;
    }

    public String getPasswordHash() {
        return Byte2String.encode(hash);
    }
}
