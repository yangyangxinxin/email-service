package com.luckysweetheart.email.message;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

/**
 * 邮件消息
 * Created by yangxin on 2017/12/26.
 */
public abstract class EmailMessage implements Serializable {

    /**
     * 邮件收件人
     */
    protected List<String> to;

    /**
     * 邮件主题
     */
    protected String subject;

    /**
     * 邮件附件
     */
    protected List<EmailAttachment> emailAttachments;

    /**
     * 邮件内容
     *
     * @return
     */
    public abstract String getContent();

    /**
     * 直接构造邮件消息。
     * 这个方法的好处在于 你可以直接通过此方法实例化一条邮件消息，不关心模板、变量等信息。
     * 可以理解为调用此方法生成的邮件消息是一条简单的纯文本消息。
     *
     * @param content 邮件消息的内容
     * @return
     */
    public static EmailMessage create(String content) {
        final String str = content;
        return new EmailMessage() {
            @Override
            public String getContent() {
                return str;
            }
        };
    }

    /**
     * 推荐使用链式调用方法 {@link EmailMessage#to(String...)}
     *
     * @param to
     */
    @Deprecated
    public void setTo(List<String> to) {
        this.to = to;
    }

    /**
     * 推荐使用链式调用方法 {@link EmailMessage#subject(String)}
     *
     * @param subject
     */
    @Deprecated
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 推荐使用链式调用方法 {@link EmailMessage#attach(File...)}
     *
     * @param emailAttachments
     */
    @Deprecated
    public void setEmailAttachments(List<EmailAttachment> emailAttachments) {
        this.emailAttachments = emailAttachments;
    }

    /**
     * 邮件收件人
     *
     * @return
     */
    public List<String> getTo() {
        return to;
    }

    /**
     * 邮件主题
     *
     * @return
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 邮件附件
     *
     * @return
     */
    public List<EmailAttachment> getEmailAttachments() {
        return emailAttachments;
    }

    /**
     * set email subject.
     *
     * @param subject
     * @return
     */
    public EmailMessage subject(String subject) {
        this.subject = subject;
        return this;
    }

    /**
     * set receivers as a List.
     */
    public EmailMessage to(List<String> to) {
        if (this.to == null) {
            this.to = new ArrayList<String>();
        }
        this.to.addAll(to);
        return this;
    }

    /**
     * set receivers .
     *
     * @param to
     * @return
     */
    public EmailMessage to(String... to) {
        if (this.to == null) {
            this.to = new ArrayList<String>();
        }
        if (to != null && to.length > 0) {
            List<String> strings = Arrays.asList(to);
            this.to.addAll(strings);
        }
        return this;
    }

    /**
     * set receivers,you can set the receivers separator , it will convert to the String Array;
     * exp:
     * <pre>
     *     String to = "981987024@qq.com,354394024@qq.com";
     *     String separator = ",";
     *
     *     EmailMessage message =  to(to,separator);
     *     System.out.println(message.getTo()); // ["981987024@qq.com,354394024@qq.com"]
     *  </pre>
     *
     * @param to
     * @param separator
     * @return
     */
    public EmailMessage to(String to, String separator) {
        if (StringUtils.isNotBlank(to)) {
            String[] strings = StringUtils.split(to, separator);
            return to(strings);
        }
        return this;
    }

    /**
     * add the email attachment with EmailAttachment Object
     *
     * @param emailAttachments
     * @return
     */
    public EmailMessage attach(EmailAttachment... emailAttachments) {
        if (this.emailAttachments == null) {
            this.emailAttachments = new ArrayList<EmailAttachment>();
        }
        if (emailAttachments != null && emailAttachments.length > 0) {
            this.emailAttachments.addAll(Arrays.asList(emailAttachments));
        }
        return this;
    }

    /**
     * add the email attachment with file byte array and file name.
     * this attachment's name and description will be set with the parameter 'name' .
     *
     * @param contents
     * @param name
     * @return
     */
    public EmailMessage attach(byte[] contents, String name) {
        if (this.emailAttachments == null) {
            this.emailAttachments = new ArrayList<EmailAttachment>();
        }
        EmailAttachment emailAttachment = new EmailAttachment();
        emailAttachment.setContents(contents);
        emailAttachment.setName(name);
        this.attach(emailAttachment);

        return this;
    }

    /**
     * add the email attachment as File Object.
     *
     * @param files
     * @return
     */
    public EmailMessage attach(File... files) {
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

    /**
     * add the email attachment as file path.
     *
     * @param path
     * @return
     */
    public EmailMessage attach(String... path) {
        if (path != null && path.length > 0) {
            for (String s : path) {
                attach(new File(s));
            }
        }
        return this;
    }

    /**
     * return the all receivers as array.
     *
     * @return
     */
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

    /**
     * return the all receivers , with separator
     *
     * @return the receivers with separator
     */
    public String getToStr(String separator) {
        if (this.to == null || this.to.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String aSendTo : this.to) {
            sb.append(aSendTo).append(separator);
        }
        return sb.toString();
    }

    /**
     * return the all receivers , with <code>;( semicolon)</code> separator
     *
     * @return the receivers with semicolon
     */
    public String getToStr() {
        if (this.to == null || this.to.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String aSendTo : this.to) {
            sb.append(aSendTo).append(";");
        }
        return sb.toString();
    }

    /**
     * 验证邮件消息的消息体是否合法。
     * 你也可以重写此方法，使用你自己的验证方法进行验证。
     */
    public void validate() {
        isTrue(getTo() != null && getTo().size() > 0, "收件人不能为空");
        isTrue(StringUtils.isNotBlank(subject), "邮件主题或标题不能为空");
    }
}
