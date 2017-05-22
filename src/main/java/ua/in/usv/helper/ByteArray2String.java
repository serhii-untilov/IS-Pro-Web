package ua.in.usv.helper;

/**
 * Created by usv on 22.05.2017.
 */
public class ByteArray2String {

    public static String convert(byte[] array){
        if (array == null)
            return "";
        int iMax = array.length - 1;
        if (iMax == -1)
            return "";
        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(Character.toString ((char) array[i]));
            if (i == iMax)
                return b.toString();
        }
    }
}
