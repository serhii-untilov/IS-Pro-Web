package ua.in.usv.helper;

import java.util.Arrays;

public class ByteArrayConvert {

    public static String toString(byte[] array) {
        if (array == null)
            return "";
        int iMax = array.length - 1;
        if (iMax == -1)
            return "";
        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            int charCode = array[i] & 0xFF;
            b.append(Character.toString((char)charCode));
            if (i == iMax)
                return b.toString();
        }
    }
}
