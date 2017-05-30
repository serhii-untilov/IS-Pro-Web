/**
 * IS-Pro TSysPuBlock implementation
 */
package ua.in.usv.stay;

import lombok.Getter;
import lombok.Setter;

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

        salt  = (long)(block[7] & 0xFF) << 24;
        salt |= (long)(block[6] & 0xFF) << 16;
        salt |= (long)(block[5] & 0xFF) << 8;
        salt |= (long)(block[4] & 0xFF);

        byte[] hash = new byte[block.length - 8];
        System.arraycopy(block, 8, hash, 0, block.length - 8);
        this.hash = hash;
    }

    public String getPasswordHash() {
        if (hash == null)
            return "";
        int iMax = hash.length - 1;
        if (iMax == -1)
            return "";
        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            int charCode = hash[i] & 0xFF;
            b.append(Character.toString((char)charCode));
            if (i == iMax)
                return b.toString();
        }
    }
}
