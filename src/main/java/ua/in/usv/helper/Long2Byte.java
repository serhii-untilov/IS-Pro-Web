package ua.in.usv.helper;

public class Long2Byte {

    public static final byte[] encode(long in) {
        byte[] out = new byte[4];
        out[3] = (byte) (in >> 24);
        out[2] = (byte) (in >> 16);
        out[1] = (byte) (in >> 8);
        out[0] = (byte) in;
        return out;
    }

    public static final long decode(byte[] in) {
        long out = in[3] << 24;
        out |= in[2] << 16;
        out |= in[1] << 8;
        out |= in[0];
        return out;
    }
}
