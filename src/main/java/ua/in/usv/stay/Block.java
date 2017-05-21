package ua.in.usv.stay;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class Block {

    private byte[] data;

    public Block(byte[] data) {
        this.data = data;
    }

    public Block(String str) {
        int length = str.length();
        byte[] header = ByteBuffer.allocate(2).putInt(length).array();
        byte[] body = str.getBytes();

        byte[] block = new byte[header.length + body.length];
        System.arraycopy(header, 0, block, 0, header.length);
        System.arraycopy(body, 0, block, header.length, body.length);

        this.data = block;
    }

    public byte[] getHeader() {
        return Arrays.copyOfRange(data, 0, 1);
    }

    public byte[] getBody() {
        return Arrays.copyOfRange(data, 2, data.length - 1);
    }

    public int getBodySize() {
        byte[] header = Arrays.copyOfRange(data, 0, 1);
        return header[0] * 10 + header[1];
    }

    @Override
    public String toString() {
        return Arrays.toString(getBody());
    }
}
