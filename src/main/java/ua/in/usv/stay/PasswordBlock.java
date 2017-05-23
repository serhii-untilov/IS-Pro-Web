package ua.in.usv.stay;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordBlock {

    public static final int digest_len  = 16;

    private byte version;
    private byte format;
    private short unused1;
    private int salt;
    private byte[] hash;

    public PasswordBlock(byte[] block){
        version = block[0];
        format = block[1];
        unused1 = 0;
        salt = (int) (((int)(block[4]) << 24) | ((int)(block[5]) << 16) | ((int)(block[6]) << 8) | ((int)(block[7])));
        byte[] hash = new byte[block.length - 8];
        System.arraycopy(hash, 0, block, 8, block.length - 8);
        this.hash = hash;
    }
}
