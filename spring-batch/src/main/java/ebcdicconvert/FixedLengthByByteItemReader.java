package ebcdicconvert;

import org.springframework.batch.item.file.LineCallbackHandler;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.batch.item.file.separator.RecordSeparatorPolicy;
import org.springframework.batch.item.file.separator.SimpleRecordSeparatorPolicy;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 按字节长度读取记录
 */
public class FixedLengthByByteItemReader<T> extends AbstractItemCountingItemStreamItemReader<T> implements
        ResourceAwareItemReaderItemStream<T>, InitializingBean {

    private RecordSeparatorPolicy recordSeparatorPolicy = new SimpleRecordSeparatorPolicy();

    private FileInputStream inputStream;

    private int lineCount = 0;

    private boolean noInput = false;

    private int lineToSkip = 0;

    private LineCallbackHandler skippedLlinesCallback;

    private boolean strict = true;

    private String filePath;

    /** 一行记录的字节长度 */
    private Integer lineByteSize;
    private Resource resource;
    private EbcdicConvent ebcdicConvent;

    @Override
    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    protected T doRead() throws Exception {

        if (noInput) {
            return null;
        }

        String line = readLine();

        if (line == null) {
            return null;
        } else {
            return (T) line;
        }
    }

    private String readLine() {
        if (inputStream == null) {
            throw new RuntimeException();
        }

        String line = null;

        try {
            line = this.readBySize();
            if (line == null) {
                return null;
            }
            lineCount++;
            while (isComment(line)) {
                line = this.readBySize();
                if (line == null) {
                    return null;
                }
                lineCount++;
            }
            line = applyRecordSepratorPolicy(line);
        } catch (Exception e) {
            noInput = true;
        }

        return line;
    }

    public String applyRecordSepratorPolicy(String line) {
        return line;
    }

    private String readBySize() throws IOException {
        if (inputStream == null) {
            inputStream = new FileInputStream(filePath);
        }
        FileChannel fileChannel = inputStream.getChannel();
        if (fileChannel.size() % lineByteSize != 0) {
            throw new RuntimeException("Unexpected end of File size before record complete" + lineCount);
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(lineByteSize);
        int length = fileChannel.read(byteBuffer);
        if (length <=0) {
            return null;
        }
        if (length != lineByteSize) {
            throw new RuntimeException("Unexpected end of read size before record complete" + lineCount);
        }

        String item = getSperate(byteBuffer.array());
        byteBuffer.clear();
        return item;
    }

    private boolean isComment(String line) {
        // 需要根据实际情况处理
        return false;
    }

    @Override
    protected void doOpen() throws Exception {

        Assert.notNull(resource,"Input resource must be set");
        Assert.notNull(recordSeparatorPolicy, "RecordSepartorPolicy must be set");

        noInput = true;
        if (!resource.exists()){
            if (strict){
                throw new RuntimeException("Input resource must exist (reader is in 'strict' mode): " + resource);
            }
            return;
        }

        if (!resource.isReadable()) {
            if (strict) {
                throw new RuntimeException("Input resource must be readable (reader is in 'strict' mode):" + resource);
            }
        }

        inputStream = new FileInputStream(resource.getFile());
        for (int i = 0; i < lineToSkip; i++) {
            String line = readLine();
            if (skippedLlinesCallback != null) {
                skippedLlinesCallback.handleLine(line);
            }
        }

        noInput = false;
    }

    @Override
    protected void doClose() throws Exception {

        lineCount = 0;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public String getSperate(byte[] bs) {

        String dataString = "";
        String tmpFieldtring = "";

        int nPos =0;
        int lTotal = 0;
        int fieldNum = 0;
        byte[] buff = new byte[bs.length + 3];  //keep reserver 3 byte;
        System.arraycopy(bs, 0, buff, 0, bs.length);
        while (true) {
            if (buff[nPos + 0] == 0x01 && buff[nPos + 1] == 0x02 && buff[nPos + 2] == 0x03) {
                byte[] field = new byte[nPos];
                System.arraycopy(buff, 0, field, 0, nPos);
                try {
                    tmpFieldtring = new String(ebcdicConvent.ebcdicToAsciiBytes(field, 0, field.length), "GBK");
                } catch (Exception e) {
                    String dataInfo = ByteUtils.toHexStr(field);;
                }

                dataString = dataString + tmpFieldtring + "~@~";
                fieldNum = fieldNum + 1;
                // GET REST BYTES TO BUFFER...
                byte[] rest = new byte[buff.length - nPos - 3];
                System.arraycopy(buff, nPos + 3, rest, 0, rest.length);
                // COPY REST TO BUFF
                buff = rest;
                lTotal = lTotal + 3;
                nPos = 0;
            }

            if (lTotal >= bs.length) {
                break;
            }
            nPos = nPos + 1;
            lTotal = lTotal + 1;
        }

        if (buff.length > 0) {
            try {
                tmpFieldtring = new String(ebcdicConvent.ebcdicToAsciiBytes(buff, 0, buff.length - 3), "GBK");
            } catch (Exception e) {
                String dataInfo = ByteUtils.toHexStr(buff);
            }
            dataString = dataString + tmpFieldtring;
        }

        return  dataString;
    }
}
