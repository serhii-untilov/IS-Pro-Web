/**
 * IS-Pro CHashMD5 implementation
 */
package ua.in.usv.stay;

import lombok.Getter;
import lombok.Setter;
import ua.in.usv.helper.Long2Byte;

import java.util.Arrays;

@Getter
@Setter
public class Md5HashEncoder {

    public static final int digest_len  = 16;

    private int[] buf = {0,0,0,0};
    private int[] bits = {0,0};
    private byte[] in = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                         0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private byte[] digest = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    private void transform(int[] buf, int[] in) {
    }

    public Md5HashEncoder() {
        init();
    }

    public void init() {
        buf[0] = 0x67452301;
        buf[1] = 0xefcdab89;
        buf[2] = 0x98badcfe;
        buf[3] = 0x10325476;

        bits[0] = 0;
        bits[1] = 0;
    }

    // The four core functions - F1 is optimized somewhat
    private long F1(long x, long y, long z) {
        return (z ^ (x & (y ^ z))) & 0x00000000ffffffffL;
    }
    private long F2(long x, long y, long z) {
        return F1(z, x, y);
    }
    private long F3(long x, long y, long z) {
        return (x ^ y ^ z) & 0x00000000ffffffffL;
    }
    private long F4(long x, long y, long z) {
        return (y ^ (x | ~z)) & 0x00000000ffffffffL;
    }

    // This is the central step in the MD5 algorithm.
    private long md5step(long f, long w, long x, long y, long z, long data, long s) {

        f &= 0x00000000ffffffffL;
        w &= 0x00000000ffffffffL;
        x &= 0x00000000ffffffffL;
        y &= 0x00000000ffffffffL;
        z &= 0x00000000ffffffffL;
        data &= 0x00000000ffffffffL;
        s &= 0x00000000ffffffffL;

        w = (w + ((f + data) & 0x00000000ffffffffL) & 0x00000000ffffffffL);
        w = ((w << s) & 0x00000000ffffffffL) | ((w >> (32-s)) & 0x00000000ffffffffL);
        w = (w + (x & 0x00000000ffffffffL) & 0x00000000ffffffffL);
        return w;
    }

    // static void MD5Transform(uint4 buf[4], uint4 const in[16])
    private void md5transform(int[] buf, int[] in)
    {
        //byte bb = -128;
        //int ii = bb & 0xFFFFFFF;
        //long ll = ii & 0xFFFF;


        long a = buf[0] & 0x00000000ffffffffL;
        long b = buf[1] & 0x00000000ffffffffL;
        long c = buf[2] & 0x00000000ffffffffL;
        long d = buf[3] & 0x00000000ffffffffL;

        a = md5step(F1(b, c, d), a, b, c, d, (long)(in[ 0] & 0x00000000ffffffffL) + 0xd76aa478,  7);
        d = md5step(F1(a, b, c), d, a, b, c, (long)(in[ 1] & 0x00000000ffffffffL) + 0xe8c7b756, 12);
        c = md5step(F1(d, a, b), c, d, a, b, (long)(in[ 2] & 0x00000000ffffffffL) + 0x242070db, 17);
        b = md5step(F1(c, d, a), b, c, d, a, (long)(in[ 3] & 0x00000000ffffffffL) + 0xc1bdceee, 22);
        a = md5step(F1(b, c, d), a, b, c, d, (long)(in[ 4] & 0x00000000ffffffffL) + 0xf57c0faf,  7);
        d = md5step(F1(a, b, c), d, a, b, c, (long)(in[ 5] & 0x00000000ffffffffL) + 0x4787c62a, 12);
        c = md5step(F1(d, a, b), c, d, a, b, (long)(in[ 6] & 0x00000000ffffffffL) + 0xa8304613, 17);
        b = md5step(F1(c, d, a), b, c, d, a, (long)(in[ 7] & 0x00000000ffffffffL) + 0xfd469501, 22);
        a = md5step(F1(b, c, d), a, b, c, d, (long)(in[ 8] & 0x00000000ffffffffL) + 0x698098d8,  7);
        d = md5step(F1(a, b, c), d, a, b, c, (long)(in[ 9] & 0x00000000ffffffffL) + 0x8b44f7af, 12);
        c = md5step(F1(d, a, b), c, d, a, b, (long)(in[10] & 0x00000000ffffffffL) + 0xffff5bb1, 17);
        b = md5step(F1(c, d, a), b, c, d, a, (long)(in[11] & 0x00000000ffffffffL) + 0x895cd7be, 22);
        a = md5step(F1(b, c, d), a, b, c, d, (long)(in[12] & 0x00000000ffffffffL) + 0x6b901122,  7);
        d = md5step(F1(a, b, c), d, a, b, c, (long)(in[13] & 0x00000000ffffffffL) + 0xfd987193, 12);
        c = md5step(F1(d, a, b), c, d, a, b, (long)(in[14] & 0x00000000ffffffffL) + 0xa679438e, 17);
        b = md5step(F1(c, d, a), b, c, d, a, (long)(in[15] & 0x00000000ffffffffL) + 0x49b40821, 22);

        a = md5step(F2(b, c, d), a, b, c, d, (long)(in[ 1] & 0x00000000ffffffffL) + 0xf61e2562,  5);
        d = md5step(F2(a, b, c), d, a, b, c, (long)(in[ 6] & 0x00000000ffffffffL) + 0xc040b340,  9);
        c = md5step(F2(d, a, b), c, d, a, b, (long)(in[11] & 0x00000000ffffffffL) + 0x265e5a51, 14);
        b = md5step(F2(c, d, a), b, c, d, a, (long)(in[ 0] & 0x00000000ffffffffL) + 0xe9b6c7aa, 20);
        a = md5step(F2(b, c, d), a, b, c, d, (long)(in[ 5] & 0x00000000ffffffffL) + 0xd62f105d,  5);
        d = md5step(F2(a, b, c), d, a, b, c, (long)(in[10] & 0x00000000ffffffffL) + 0x02441453,  9);
        c = md5step(F2(d, a, b), c, d, a, b, (long)(in[15] & 0x00000000ffffffffL) + 0xd8a1e681, 14);
        b = md5step(F2(c, d, a), b, c, d, a, (long)(in[ 4] & 0x00000000ffffffffL) + 0xe7d3fbc8, 20);
        a = md5step(F2(b, c, d), a, b, c, d, (long)(in[ 9] & 0x00000000ffffffffL) + 0x21e1cde6,  5);
        d = md5step(F2(a, b, c), d, a, b, c, (long)(in[14] & 0x00000000ffffffffL) + 0xc33707d6,  9);
        c = md5step(F2(d, a, b), c, d, a, b, (long)(in[ 3] & 0x00000000ffffffffL) + 0xf4d50d87, 14);
        b = md5step(F2(c, d, a), b, c, d, a, (long)(in[ 8] & 0x00000000ffffffffL) + 0x455a14ed, 20);
        a = md5step(F2(b, c, d), a, b, c, d, (long)(in[13] & 0x00000000ffffffffL) + 0xa9e3e905,  5);
        d = md5step(F2(a, b, c), d, a, b, c, (long)(in[ 2] & 0x00000000ffffffffL) + 0xfcefa3f8,  9);
        c = md5step(F2(d, a, b), c, d, a, b, (long)(in[ 7] & 0x00000000ffffffffL) + 0x676f02d9, 14);
        b = md5step(F2(c, d, a), b, c, d, a, (long)(in[12] & 0x00000000ffffffffL) + 0x8d2a4c8a, 20);

        a = md5step(F3(b, c, d), a, b, c, d, (long)(in[ 5] & 0x00000000ffffffffL) + 0xfffa3942,  4);
        d = md5step(F3(a, b, c), d, a, b, c, (long)(in[ 8] & 0x00000000ffffffffL) + 0x8771f681, 11);
        c = md5step(F3(d, a, b), c, d, a, b, (long)(in[11] & 0x00000000ffffffffL) + 0x6d9d6122, 16);
        b = md5step(F3(c, d, a), b, c, d, a, (long)(in[14] & 0x00000000ffffffffL) + 0xfde5380c, 23);
        a = md5step(F3(b, c, d), a, b, c, d, (long)(in[ 1] & 0x00000000ffffffffL) + 0xa4beea44,  4);
        d = md5step(F3(a, b, c), d, a, b, c, (long)(in[ 4] & 0x00000000ffffffffL) + 0x4bdecfa9, 11);
        c = md5step(F3(d, a, b), c, d, a, b, (long)(in[ 7] & 0x00000000ffffffffL) + 0xf6bb4b60, 16);
        b = md5step(F3(c, d, a), b, c, d, a, (long)(in[10] & 0x00000000ffffffffL) + 0xbebfbc70, 23);
        a = md5step(F3(b, c, d), a, b, c, d, (long)(in[13] & 0x00000000ffffffffL) + 0x289b7ec6,  4);
        d = md5step(F3(a, b, c), d, a, b, c, (long)(in[ 0] & 0x00000000ffffffffL) + 0xeaa127fa, 11);
        c = md5step(F3(d, a, b), c, d, a, b, (long)(in[ 3] & 0x00000000ffffffffL) + 0xd4ef3085, 16);
        b = md5step(F3(c, d, a), b, c, d, a, (long)(in[ 6] & 0x00000000ffffffffL) + 0x04881d05, 23);
        a = md5step(F3(b, c, d), a, b, c, d, (long)(in[ 9] & 0x00000000ffffffffL) + 0xd9d4d039,  4);
        d = md5step(F3(a, b, c), d, a, b, c, (long)(in[12] & 0x00000000ffffffffL) + 0xe6db99e5, 11);
        c = md5step(F3(d, a, b), c, d, a, b, (long)(in[15] & 0x00000000ffffffffL) + 0x1fa27cf8, 16);
        b = md5step(F3(c, d, a), b, c, d, a, (long)(in[ 2] & 0x00000000ffffffffL) + 0xc4ac5665, 23);

        a = md5step(F4(b, c, d), a, b, c, d, (long)(in[ 0] & 0x00000000ffffffffL) + 0xf4292244, 6);
        d = md5step(F4(a, b, c), d, a, b, c, (long)(in[ 7] & 0x00000000ffffffffL) + 0x432aff97, 10);
        c = md5step(F4(d, a, b), c, d, a, b, (long)(in[14] & 0x00000000ffffffffL) + 0xab9423a7, 15);
        b = md5step(F4(c, d, a), b, c, d, a, (long)(in[ 5] & 0x00000000ffffffffL) + 0xfc93a039, 21);
        a = md5step(F4(b, c, d), a, b, c, d, (long)(in[12] & 0x00000000ffffffffL) + 0x655b59c3, 6);
        d = md5step(F4(a, b, c), d, a, b, c, (long)(in[ 3] & 0x00000000ffffffffL) + 0x8f0ccc92, 10);
        c = md5step(F4(d, a, b), c, d, a, b, (long)(in[10] & 0x00000000ffffffffL) + 0xffeff47d, 15);
        b = md5step(F4(c, d, a), b, c, d, a, (long)(in[ 1] & 0x00000000ffffffffL) + 0x85845dd1, 21);
        a = md5step(F4(b, c, d), a, b, c, d, (long)(in[ 8] & 0x00000000ffffffffL) + 0x6fa87e4f, 6);
        d = md5step(F4(a, b, c), d, a, b, c, (long)(in[15] & 0x00000000ffffffffL) + 0xfe2ce6e0, 10);
        c = md5step(F4(d, a, b), c, d, a, b, (long)(in[ 6] & 0x00000000ffffffffL) + 0xa3014314, 15);
        b = md5step(F4(c, d, a), b, c, d, a, (long)(in[13] & 0x00000000ffffffffL) + 0x4e0811a1, 21);
        a = md5step(F4(b, c, d), a, b, c, d, (long)(in[ 4] & 0x00000000ffffffffL) + 0xf7537e82, 6);
        d = md5step(F4(a, b, c), d, a, b, c, (long)(in[11] & 0x00000000ffffffffL) + 0xbd3af235, 10);
        c = md5step(F4(d, a, b), c, d, a, b, (long)(in[ 2] & 0x00000000ffffffffL) + 0x2ad7d2bb, 15);
        b = md5step(F4(c, d, a), b, c, d, a, (long)(in[ 9] & 0x00000000ffffffffL) + 0xeb86d391, 21);

        buf[0] += (int)(a & 0x00000000ffffffffL);
        buf[1] += (int)(b & 0x00000000ffffffffL);
        buf[2] += (int)(c & 0x00000000ffffffffL);
        buf[3] += (int)(d & 0x00000000ffffffffL);
    }

    private void md5transform(int[] buf, byte[] in) {
        int[] inInt = new int[16];
        byteArray2intArray(in, inInt);
        md5transform(buf, inInt);
        intArray2byteArray(inInt, in);
    }


    private void byteArray2intArray(byte[] ba, int[] ia) {
        for (int i = 0; i < ia.length; i++) {
            ia[i]  = (int)(ba[i * 4 + 3] & 0xFF) << 24;
            ia[i] |= (int)(ba[i * 4 + 2] & 0xFF) << 16;
            ia[i] |= (int)(ba[i * 4 + 1] & 0xFF) << 8;
            ia[i] |= (int)(ba[i * 4    ] & 0xFF);
        }
    }

    private void intArray2byteArray(int[] ia, byte[] ba) {
        for (int i = 0; i < ia.length; i++) {
            ba[i * 4 + 3] = (byte)(ia[i] >> 24);
            ba[i * 4 + 2] = (byte)(ia[i] >> 16);
            ba[i * 4 + 1] = (byte)(ia[i] >> 8);
            ba[i * 4    ] = (byte)(ia[i]);
        }
    }

    public void update(byte[] buf_p, int len) {
        // Update bitcount
        long  t = bits[0] & 0x00000000ffffffffL;
        bits[0] = (int)((t + (len << 3)) & 0x00000000ffffffffL);

        if ((bits[0] & 0x00000000ffffffffL) < t)
            bits[1] = (int)((bits[1] & 0x00000000ffffffffL) + 1);  // Carry from low to high
        bits[1] = (int)((bits[1] & 0x00000000ffffffffL) + ((len >> 29) & 0x00000000ffffffffL));

        // Compute number of bytes mod 64
        t = (t >> 3) & 0x3f;	// Bytes already in shsInfo->data

        // Handle any leading odd-sized chunks
        t = (int)(t & 0x00000000ffffffffL);
        int i = 0;
        if (t != 0) {
            int p = (int)t;
            t = 64 - t;
            if (len < t) {
                System.arraycopy(buf_p, i, in, p, len);
                return;
            }
            System.arraycopy(buf_p, i, in, p, (int)t);
            md5transform(buf, in);
            i += t;
            len -= t;
        }

        // Process dat  a in 64-byte chunks
        while (len >= 64) {
            System.arraycopy(buf_p, i, in, 0, 64);
            md5transform(buf, in);
            i += 64;
            len -= 64;
        }

        // Handle any remaining bytes of data
        if (len > 0)
            System.arraycopy(buf_p, i, in, 0, len);
    }

    // Final wrapup - pad to 64-byte boundary with the bit pattern
    // 1 0* (64-bit count of bits processed, MSB-first)
    public void flush() {

        // Compute number of bytes mod 64
        long count = (bits[0] >> 3) & 0x3F;

        // Set the first char of padding to 0x80.  This is safe since there is always at least one byte free
        int p = (int)count;
        in[p] = (byte)0x80;
        p++;

        // Bytes of padding needed to make 64 bytes
        count = 64 - 1 - count;

        // Pad out to 56 mod 64
        if (count < 8) {
	        // Two lots of padding:  Pad the first block to 64 bytes
            Arrays.fill(in, p, p + (int)count - 1, (byte) 0);
            md5transform(buf, in);
	        // Now fill the next block with 56 bytes
            Arrays.fill(in, 0, 56, (byte) 0);
        } else {
	        // Pad block to 56 bytes
            Arrays.fill(in, p, p + (int)count - 8 - 1, (byte) 0);
        }

        // Append length in bits and transform
        // ((uint4 *) in)[14] = bits[0];
        in[56] = (byte)bits[0];
        in[57] = (byte)(bits[0] >> 8);
        in[58] = (byte)(bits[0] >> 16);
        in[59] = (byte)(bits[0] >> 24);
        //((uint4 *) in)[15] = bits[1];
        in[60] = (byte)bits[1];
        in[61] = (byte)(bits[1] >> 8);
        in[62] = (byte)(bits[1] >> 16);
        in[63] = (byte)(bits[1] >> 24);

        md5transform(buf, in);

        //System.arraycopy(buf, 0, digest, 0, 16);
        setDigest(buf);

        init();
    }

    private void setDigest(int[] buf4) {
        for (int i = 0; i < buf.length; i++) {
            digest[i * 4 + 3] = (byte)((buf[i] >> 24) & 0xFF);
            digest[i * 4 + 2] = (byte)((buf[i] >> 16) & 0xFF);
            digest[i * 4 + 1] = (byte)((buf[i] >> 8) & 0xFF);
            digest[i * 4    ] = (byte)((buf[i]) & 0xFF);
        }
    }

    // int STAYPROC s_MD5_GenerateHash(const char * string_to_hash, uint4 salt, void * output)
    public int generateHash(String str, long salt, byte[] output)
    {
        init();

        byte[] saltBytes = Long2Byte.encode(salt);
        update(saltBytes, saltBytes.length);

        byte[] strBytes = str.getBytes();
        update(strBytes, strBytes.length);

        flush();

        System.arraycopy(digest, 0, output, 0, output.length);

        return digest_len;
    }

    public int generateHash(long key, String str, long salt, byte[] output)
    {
        init();

        byte[] saltBytes = Long2Byte.encode(salt);
        update(saltBytes, saltBytes.length);

        byte[] keyBytes = Long2Byte.encode(key);
        update(keyBytes, keyBytes.length);

        byte[] strBytes = str.getBytes();
        update(strBytes, strBytes.length);

        flush();
        System.arraycopy(digest, 0, output, 0, output.length);

        return digest_len;
    }
}
