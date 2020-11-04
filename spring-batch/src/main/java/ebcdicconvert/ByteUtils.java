package ebcdicconvert;

public class ByteUtils {

    private static final char[] HEX_DIGITS = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

    public static String toHexStr(byte[] bs) {

        return toHexStr(bs, false);
    }

    /**
     *
     * @param bs
     * @param sep 字节之间是否添加空格分隔符
     * @return
     */
    public static String toHexStr(byte[] bs, boolean sep) {

        StringBuilder sb = new StringBuilder(bs.length * (sep ? 3 : 2));
        byte byte0;
        for (int i = 0; i < bs.length; ++i){
            byte0 = bs[i];
            // 取字节中高4位的数字转换, >>> 为逻辑右移，将符号位一起右移
            sb.append(HEX_DIGITS[byte0 >>> 4 & 0xf]);
            // 取字节中低4位的数字转换
            sb.append(HEX_DIGITS[byte0 & 0xf]);
            if (sep) {
                sb.append(' ');
            }
        }

        return sb.toString();

    }

    /**
     * 16进制串转字节数组
     * @param hex
     * @return
     */
    public static byte[] hexStr2Bytes(String hex) {

        int i = 0;
        byte[] data = new byte[hex.length() / 2];
        int step = 2;
        for (int n = 0; n < hex.length(); n += step) {
            String temp = hex.substring(n, n + step);
            int tt = Integer.parseInt(temp, 16);
            data[i] = (byte) tt;
            i++;
        }

        return data;
    }

    public static int byte2Int(byte[] bs) {
        return (int) byte2Long(bs);
    }

    public static long byte2Long(byte[] bs) {

        long ret = 0;
        int length = bs.length;
        for (int i = 0; i < length; i++) {
            // 左移8位
            ret += ((long) (bs[i] & 0xff) << (length - 1 - i) * 8);
        }
        return ret;
    }


}
