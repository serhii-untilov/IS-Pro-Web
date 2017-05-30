package ua.in.usv.helper;

public class Long2Byte {

    public static byte[] encode(long in) {
        byte[] out = new byte[4];
        out[3] = (byte) (in >> 24);
        out[2] = (byte) (in >> 16);
        out[1] = (byte) (in >> 8);
        out[0] = (byte) in;
        return out;
    }

    public static long decode(byte[] in, int offset) {
        long out = (long)(in[offset + 3] & 0xFF) << 24;
        out |= (long)(in[offset + 2] & 0xFF) << 16;
        out |= (long)(in[offset + 1] & 0xFF) << 8;
        out |= (long)(in[offset] & 0xFF);
        return out;
    }
}
