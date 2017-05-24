/**
 * IS-Pro CHashMD5 implementation
 */
package ua.in.usv.stay;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordEncoder {

    private int[] buf = {0,0,0,0};
    private int[] bits = {0,0};
    private byte[] in = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                         0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private byte[] digest = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    private void transform(int[] buf, int[] in) {
    }

    public PasswordEncoder() {
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

    private void byteReverse(byte[] buf, int longs) {
        int p = 0;
        do {
            int t  = (short)buf[3] << 8;
            t |= buf[2] << 16;
            t |= (short)buf[1] << 8;
            t |= buf[0];

	        buf[p] = (byte)(t >> 24);
	        buf[p+1] = (byte)(t >> 16);
	        buf[p+2] = (byte)(t >> 8);
	        buf[p+3] = (byte)t;

            p++;
        } while (--longs > 0);
    }

    // The four core functions - F1 is optimized somewhat
    //#define F1(x, y, z) (z ^ (x & (y ^ z)))
    private int F1(int x, int y, int z) {
        return (z ^ (x & (y ^ z)));
    }
    //#define F2(x, y, z) F1(z, x, y)
    private int F2(int x, int y, int z) {
        return F1(x, y, z);
    }
    //#define F3(x, y, z) (x ^ y ^ z)
    private int F3(int x, int y, int z) {
        return (x ^ y ^ z);
    }
    //#define F4(x, y, z) (y ^ (x | ~z))
    private int F4(int x, int y, int z) {
        return (y ^ (x | ~z));
    }

    // This is the central step in the MD5 algorithm.
    // #define MD5STEP(f, w, x, y, z, data, s) \
    //        ( w += f(x, y, z) + data,  w = w<<s | w>>(32-s),  w += x )
    private int md5step(int f, int w, int x, int y, int z, int data, int s) {
        return ( w += f(x, y, z) + data,  w = w<<s | w>>(32-s),  w += x )
    }


    // static void MD5Transform(uint4 buf[4], uint4 const in[16])
    private void md5Transform(int buf[], int in[])
    {
        int a = buf[0];
        int b = buf[1];
        int c = buf[2];
        int d = buf[3];

        MD5STEP(F1, a, b, c, d, in[0] + 0xd76aa478, 7);
        MD5STEP(F1, d, a, b, c, in[1] + 0xe8c7b756, 12);
        MD5STEP(F1, c, d, a, b, in[2] + 0x242070db, 17);
        MD5STEP(F1, b, c, d, a, in[3] + 0xc1bdceee, 22);
        MD5STEP(F1, a, b, c, d, in[4] + 0xf57c0faf, 7);
        MD5STEP(F1, d, a, b, c, in[5] + 0x4787c62a, 12);
        MD5STEP(F1, c, d, a, b, in[6] + 0xa8304613, 17);
        MD5STEP(F1, b, c, d, a, in[7] + 0xfd469501, 22);
        MD5STEP(F1, a, b, c, d, in[8] + 0x698098d8, 7);
        MD5STEP(F1, d, a, b, c, in[9] + 0x8b44f7af, 12);
        MD5STEP(F1, c, d, a, b, in[10] + 0xffff5bb1, 17);
        MD5STEP(F1, b, c, d, a, in[11] + 0x895cd7be, 22);
        MD5STEP(F1, a, b, c, d, in[12] + 0x6b901122, 7);
        MD5STEP(F1, d, a, b, c, in[13] + 0xfd987193, 12);
        MD5STEP(F1, c, d, a, b, in[14] + 0xa679438e, 17);
        MD5STEP(F1, b, c, d, a, in[15] + 0x49b40821, 22);

        MD5STEP(F2, a, b, c, d, in[1] + 0xf61e2562, 5);
        MD5STEP(F2, d, a, b, c, in[6] + 0xc040b340, 9);
        MD5STEP(F2, c, d, a, b, in[11] + 0x265e5a51, 14);
        MD5STEP(F2, b, c, d, a, in[0] + 0xe9b6c7aa, 20);
        MD5STEP(F2, a, b, c, d, in[5] + 0xd62f105d, 5);
        MD5STEP(F2, d, a, b, c, in[10] + 0x02441453, 9);
        MD5STEP(F2, c, d, a, b, in[15] + 0xd8a1e681, 14);
        MD5STEP(F2, b, c, d, a, in[4] + 0xe7d3fbc8, 20);
        MD5STEP(F2, a, b, c, d, in[9] + 0x21e1cde6, 5);
        MD5STEP(F2, d, a, b, c, in[14] + 0xc33707d6, 9);
        MD5STEP(F2, c, d, a, b, in[3] + 0xf4d50d87, 14);
        MD5STEP(F2, b, c, d, a, in[8] + 0x455a14ed, 20);
        MD5STEP(F2, a, b, c, d, in[13] + 0xa9e3e905, 5);
        MD5STEP(F2, d, a, b, c, in[2] + 0xfcefa3f8, 9);
        MD5STEP(F2, c, d, a, b, in[7] + 0x676f02d9, 14);
        MD5STEP(F2, b, c, d, a, in[12] + 0x8d2a4c8a, 20);

        MD5STEP(F3, a, b, c, d, in[5] + 0xfffa3942, 4);
        MD5STEP(F3, d, a, b, c, in[8] + 0x8771f681, 11);
        MD5STEP(F3, c, d, a, b, in[11] + 0x6d9d6122, 16);
        MD5STEP(F3, b, c, d, a, in[14] + 0xfde5380c, 23);
        MD5STEP(F3, a, b, c, d, in[1] + 0xa4beea44, 4);
        MD5STEP(F3, d, a, b, c, in[4] + 0x4bdecfa9, 11);
        MD5STEP(F3, c, d, a, b, in[7] + 0xf6bb4b60, 16);
        MD5STEP(F3, b, c, d, a, in[10] + 0xbebfbc70, 23);
        MD5STEP(F3, a, b, c, d, in[13] + 0x289b7ec6, 4);
        MD5STEP(F3, d, a, b, c, in[0] + 0xeaa127fa, 11);
        MD5STEP(F3, c, d, a, b, in[3] + 0xd4ef3085, 16);
        MD5STEP(F3, b, c, d, a, in[6] + 0x04881d05, 23);
        MD5STEP(F3, a, b, c, d, in[9] + 0xd9d4d039, 4);
        MD5STEP(F3, d, a, b, c, in[12] + 0xe6db99e5, 11);
        MD5STEP(F3, c, d, a, b, in[15] + 0x1fa27cf8, 16);
        MD5STEP(F3, b, c, d, a, in[2] + 0xc4ac5665, 23);

        MD5STEP(F4, a, b, c, d, in[0] + 0xf4292244, 6);
        MD5STEP(F4, d, a, b, c, in[7] + 0x432aff97, 10);
        MD5STEP(F4, c, d, a, b, in[14] + 0xab9423a7, 15);
        MD5STEP(F4, b, c, d, a, in[5] + 0xfc93a039, 21);
        MD5STEP(F4, a, b, c, d, in[12] + 0x655b59c3, 6);
        MD5STEP(F4, d, a, b, c, in[3] + 0x8f0ccc92, 10);
        MD5STEP(F4, c, d, a, b, in[10] + 0xffeff47d, 15);
        MD5STEP(F4, b, c, d, a, in[1] + 0x85845dd1, 21);
        MD5STEP(F4, a, b, c, d, in[8] + 0x6fa87e4f, 6);
        MD5STEP(F4, d, a, b, c, in[15] + 0xfe2ce6e0, 10);
        MD5STEP(F4, c, d, a, b, in[6] + 0xa3014314, 15);
        MD5STEP(F4, b, c, d, a, in[13] + 0x4e0811a1, 21);
        MD5STEP(F4, a, b, c, d, in[4] + 0xf7537e82, 6);
        MD5STEP(F4, d, a, b, c, in[11] + 0xbd3af235, 10);
        MD5STEP(F4, c, d, a, b, in[2] + 0x2ad7d2bb, 15);
        MD5STEP(F4, b, c, d, a, in[9] + 0xeb86d391, 21);

        buf[0] += a;
        buf[1] += b;
        buf[2] += c;
        buf[3] += d;
    }


    public void update(byte[] buf_p, short len) {

        // Update bitcount
        long t = (long)(bits[0] & 0xFFFFFFFF);
        bits[0] = (int)(t + ((long)len << 3));
        if ((long)(bits[0] & 0xFFFFFFFF) < t)
            bits[1] = (int)((long)(bits[1] & 0xFFFFFFFF) + 1);  // Carry from low to high
        bits[1] = (int)((long)(bits[1] & 0xFFFFFFFF) + ((long)len >> 29));

        t = (t >> 3) & 0x3f;	// Bytes already in shsInfo->data

        // Handle any leading odd-sized chunks
        if (t != 0) {
            long p = t;

            t = 64 - t;
            if (len < t) {
                System.arraycopy(buf_p, 0, buf, (int)p, len);
                return;
            }
            System.arraycopy(buf_p, 0, buf, (int)p, (int)t);

            byteReverse(in, 16);
            MD5Transform(buf, (uint4 *) in);
            buf_p += t;
            len -= t;
        }

    /* Process data in 64-byte chunks */

        while (len >= 64) {
            memcpy(in, buf_p, 64);
            byteReverse(in, 16);
            MD5Transform(buf, (uint4 *) in);
            buf_p += 64;
            len -= 64;
        }

    /* Handle any remaining bytes of data. */

        memcpy(in, buf_p, len);

    }

    public void flush() {
    }
}
