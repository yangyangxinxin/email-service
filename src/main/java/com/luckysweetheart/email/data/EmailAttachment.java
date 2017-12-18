package com.luckysweetheart.email.data;

import org.apache.commons.io.IOUtils;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * 邮件附件
 * Created by yangxin on 2017/12/18.
 */
public class EmailAttachment implements Serializable {

    /**
     * 附件名
     */
    private String name;

    /**
     * 附件描述
     */
    private String description;

    /**
     * 附件字节数组
     */
    private byte[] contents;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    public DataSource createDataSource() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(getContents());
        DataSource data = new ByteArrayDataSource(inputStream,"application/octet-stream");
        IOUtils.closeQuietly(inputStream);
        return data;
    }
}
