package com.luckysweetheart.email.data;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 邮件消息
 * Created by yangxin on 2017/12/18.
 */
public class EmailMessageData implements Serializable {

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 主题
     */
    private String subject;

    /**
     * 收件人
     */
    private List<String> to;

    /**
     * 邮件附件
     */
    private List<EmailAttachment> emailAttachments;

    private EmailMessageData() {

    }

    public static EmailMessageData create() {
        return new EmailMessageData();
    }

    public EmailMessageData content(String content) {
        this.content = content;
        return this;
    }

    public EmailMessageData subject(String subject) {
        this.subject = subject;
        return this;
    }

    public EmailMessageData to(List<String> to) {
        if (this.to == null) {
            this.to = new ArrayList<String>();
        }
        this.to.addAll(to);
        return this;
    }

    public EmailMessageData to(String... to) {
        if (this.to == null) {
            this.to = new ArrayList<String>();
        }
        if (to != null && to.length > 0) {
            List<String> strings = Arrays.asList(to);
            this.to.addAll(strings);
        }
        return this;
    }

    public EmailMessageData to(String to, String separator) {
        if (StringUtils.isNotBlank(to)) {
            String[] strings = StringUtils.split(to, separator);
            return to(strings);
        }
        return this;
    }

    public EmailMessageData attach(EmailAttachment... emailAttachments) {
        if (this.emailAttachments == null) {
            this.emailAttachments = new ArrayList<EmailAttachment>();
        }
        if (emailAttachments != null && emailAttachments.length > 0) {
            this.emailAttachments.addAll(Arrays.asList(emailAttachments));
        }
        return this;
    }

    public EmailMessageData attach(byte[] contents, String name) {
        if (this.emailAttachments == null) {
            this.emailAttachments = new ArrayList<EmailAttachment>();
        }
        EmailAttachment emailAttachment = new EmailAttachment();
        emailAttachment.setContents(contents);
        emailAttachment.setDescription(name);
        emailAttachment.setName(name);
        this.attach(emailAttachment);

        return this;
    }

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Developer5\\Desktop\\LuckDraw\\images\\bg.png");
        System.out.println(file.getName());
    }

    public EmailMessageData attach(File... files) {
        try {
            if (files != null && files.length > 0) {
                for (File file : files) {
                    attach(FileUtils.readFileToByteArray(file), file.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public EmailMessageData attach(String... path) {
        if (path != null && path.length > 0) {
            for (String s : path) {
                attach(new File(s));
            }
        }
        return this;
    }

    public String[] getToArray() {
        if (this.to == null || this.to.size() == 0) {
            return null;
        }
        try {
            String[] arr = new String[this.to.size()];
            for (int i = 0, length = this.to.size(); i < length; i++) {
                arr[i] = this.to.get(i);
            }
            return arr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getToList() {
        if (this.to == null || this.to.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String aSendTo : this.to) {
            sb.append(aSendTo).append(";");
        }
        return sb.toString();
    }

    public String getContent() {
        return content;
    }

    public String getSubject() {
        return subject;
    }

    public List<String> getTo() {
        return to;
    }

    public List<EmailAttachment> getEmailAttachments() {
        return emailAttachments;
    }
}
