package ua.in.usv.stay;

import ua.in.usv.helper.ByteArray2String;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Arrays;

public class Block {

    private byte[] data;

    public Block(byte[] data) {
        this.data = data;
    }

    public Block(String str) {
        int length = str.length();
        byte[] header = {0,0};
        length += 2;
        header[0] = (byte) (length >> 8);
        header[1] = (byte) (length);
        byte[] body = str.getBytes();

        byte[] block = new byte[header.length + body.length];
        System.arraycopy(header, 0, block, 0, header.length);
        System.arraycopy(body, 0, block, header.length, body.length);

        this.data = block;
    }

    public byte[] getHeader() {
        return Arrays.copyOfRange(data, 0, 2);
    }

    public byte[] getBody() {
        return Arrays.copyOfRange(data, 2, data.length);
    }

    public int getSize() {
        byte[] header = getHeader();
        int size = (header[0] << 8) | header[1];
        return size;
    }

    @Override
    public String toString() {
        byte[] body = getBody();
        return ByteArray2String.convert(body);
    }
}
