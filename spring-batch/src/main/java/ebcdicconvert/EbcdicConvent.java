package ebcdicconvert;

import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class EbcdicConvent {

    /** 转码字符集合 */
    public static byte[][] dataCode = new byte[65535][];

    /**
     * EBCDIC码表加载
     */
    @PostConstruct
    public static void init() throws IOException {

        Properties ppt = new Properties();

        ClassPathResource cpr = new ClassPathResource("/fileConvent.properties");
        ppt.load(cpr.getInputStream());

        for (Map.Entry<Object, Object> entrySet : ppt.entrySet()) {
            String key = (String)entrySet.getKey();
            String value = (String)entrySet.getValue();
            try {
                dataCode[ByteUtils.byte2Int(ByteUtils.hexStr2Bytes(key))] = ByteUtils.hexStr2Bytes(value);
            } catch (Exception e) {

                break;
            }
        }

        System.out.println("EBCDIC码表加载成功");

    }

    /**
     * EBCDIC转码处理
     * @param e
     * @param offset
     * @param len
     * @return
     */
    public byte[] ebcdicToAsciiBytes(byte[] e, int offset, int len) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] defaultBytes = ByteUtils.hexStr2Bytes("A1A1");
        int n = 0;
        for (int i = 0; i < len; i++) {
            // 中文特殊处理 开始符0E 结束符0F  均直接处理为空格占位
            if (e[i] == 0x0E) {
                i++;
                while(e[i] != 0x0F) {
                    byte[] tmpBs = new byte[2];
                    tmpBs[0] = e[i++];
                    tmpBs[1] = e[i++];
                    // 行结束未有0X0F 异常处理
                    if (i >= len) {
                        System.out.println("数据转码失败，未匹配到0X0F" + ":" + ByteUtils.toHexStr(e));
                        baos.close();
                        throw new RuntimeException();
                    }
                    n = ByteUtils.byte2Int(tmpBs);
                    try {
                        baos.write(dataCode[n]);
                    } catch (Exception ex) {
                        baos.write(defaultBytes);
                    }

                }
            } else {
                n = e[i]& 0xff;
                baos.write(dataCode[n]);
            }
        }

        baos.close();
        return baos.toByteArray();
    }

}
