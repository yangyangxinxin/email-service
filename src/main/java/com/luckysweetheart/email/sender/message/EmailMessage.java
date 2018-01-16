package com.luckysweetheart.email.sender.message;

import com.luckysweetheart.email.sender.client.EmailClient;
import com.luckysweetheart.email.sender.exception.EmailMessageException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.util.Assert.isTrue;

/**
 * 邮件消息
 * Created by yangxin on 2017/12/26.
 */
public abstract class EmailMessage implements Serializable {

    /**
     * 默认分隔符
     */
    public static final String DEFAULT_SEPARATOR = ",";

    /**
     * 邮件收件人
     * The "To" (primary) recipients.
     */
    protected List<String> to;

    /**
     * 抄送
     * The "Cc" (carbon copy) recipients.
     */
    protected List<String> cc;

    /**
     * 密送
     * The "Bcc" (blind carbon copy) recipients.
     */
    protected List<String> bcc;

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

    public List<String> getCc() {
        return cc;
    }

    /**
     * 推荐使用链式调用方法 {@link EmailMessage#cc(String...)}
     *
     * @param cc
     */
    @Deprecated
    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    public List<String> getBcc() {
        return bcc;
    }

    /**
     * 推荐使用链式调用方法 {@link EmailMessage#bcc(String...)}
     *
     * @param bcc
     */
    @Deprecated
    public void setBcc(List<String> bcc) {
        this.bcc = bcc;
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

    public EmailMessage cc(List<String> cc) {
        if (this.cc == null) {
            this.cc = new ArrayList<>();
        }
        this.cc.addAll(cc);
        return this;
    }

    public EmailMessage bcc(List<String> bcc) {
        if (this.bcc == null) {
            this.bcc = new ArrayList<>();
        }
        this.bcc.addAll(bcc);
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
            this.to = new ArrayList<>();
        }
        if (to != null && to.length > 0) {
            List<String> strings = Arrays.asList(to);
            this.to.addAll(strings);
        }
        return this;
    }

    public EmailMessage cc(String... cc) {
        if (this.cc == null) {
            this.cc = new ArrayList<>();
        }
        if (cc != null && cc.length > 0) {
            List<String> strings = Arrays.asList(cc);
            this.cc.addAll(strings);
        }
        return this;
    }

    public EmailMessage bcc(String... bcc) {
        if (this.bcc == null) {
            this.bcc = new ArrayList<>();
        }
        if (bcc != null && bcc.length > 0) {
            List<String> strings = Arrays.asList(bcc);
            this.bcc.addAll(strings);
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

    public EmailMessage to(String to) {
        return to(to, DEFAULT_SEPARATOR);
    }

    public EmailMessage cc(String cc, String separator) {
        if (StringUtils.isNotBlank(cc)) {
            String[] strings = StringUtils.split(cc, separator);
            return cc(strings);
        }
        return this;
    }

    public EmailMessage cc(String cc) {
        return cc(cc, ",");
    }

    public EmailMessage bcc(String bcc, String separator) {
        if (StringUtils.isNotBlank(bcc)) {
            String[] strings = StringUtils.split(bcc, separator);
            return bcc(strings);
        }
        return this;
    }

    public EmailMessage bcc(String bcc) {
        return bcc(bcc, ",");
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

    public String[] getArray(List<String> list) {
        if (list == null || to.size() == 0) {
            return null;
        }
        try {
            String[] arr = new String[list.size()];
            for (int i = 0, length = list.size(); i < length; i++) {
                arr[i] = list.get(i);
            }
            return arr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getListStr(List<String> list, String separator) {
        if (list == null || list.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String aSendTo : list) {
            sb.append(aSendTo).append(separator);
        }
        return sb.toString();
    }

    public String getListStr(List<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String aSendTo : list) {
            sb.append(aSendTo).append(DEFAULT_SEPARATOR);
        }
        return sb.toString();
    }

    /**
     * return the all receivers as array.
     *
     * @return
     */
    public String[] getToArray() {
        return getArray(this.to);
    }

    public String[] getCcArray() {
        return getArray(this.cc);
    }

    public String[] getBccArray() {
        return getArray(this.bcc);
    }

    /**
     * return the all receivers , with separator
     *
     * @return the receivers with separator
     */
    public String getToStr(String separator) {
        return getListStr(to, separator);
    }

    public String getCcStr(String separator) {
        return getListStr(cc, separator);
    }

    public String getBccStr(String separator) {
        return getListStr(bcc, separator);
    }

    /**
     * return the all receivers , with <code>,( comma)</code> separator
     *
     * @return the receivers with comma
     */
    public String getToStr() {
        return getListStr(to);
    }

    public String getCcStr() {
        return getListStr(cc);
    }

    public String getBccStr() {
        return getListStr(bcc);
    }

    /**
     * 验证邮件消息的消息体是否合法。
     * 你也可以重写此方法，使用你自己的验证方法进行验证。
     */
    public void validate() {
        isTrue(getTo() != null && getTo().size() > 0, "收件人不能为空");
        isTrue(StringUtils.isNotBlank(subject), "邮件主题或标题不能为空");
    }

    @Override
    public String toString() {
        return "EmailMessage{" + "收件人：" + to +
                ", 抄送：" + cc +
                ", 密送：" + bcc +
                ", 主题：'" + subject + '\'' +
                ", 附件：" + emailAttachments +
                ", 内容：'" + getContent() + "\'" +
                '}';
    }

    /**
     * 构建MimeMessage
     *
     * @param emailClient
     * @return
     */
    public MimeMessage buildMimeMessage(EmailClient emailClient) {
        try {
            MimeMessage message = emailClient.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // 附件
            List<EmailAttachment> emailAttachments = getEmailAttachments();
            if (emailAttachments != null && emailAttachments.size() > 0) {
                for (EmailAttachment emailAttachment : emailAttachments) {
                    helper.addAttachment(emailAttachment.getName(), emailAttachment.createDataSource());
                }
            }

            // 发件人，从配置中取
            helper.setFrom(emailClient.getUsername());

            // 收件人
            helper.setTo(getToArray());

            // 抄送
            String[] ccArray = getCcArray();
            if (ccArray != null) {
                helper.setCc(ccArray);
            }

            // 密送
            String[] bccArray = getBccArray();
            if (bccArray != null) {
                helper.setBcc(bccArray);
            }

            // 主题
            helper.setSubject(getSubject());

            // 邮件内容
            helper.setText(getContent(), true);
            return message;
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
            throw new EmailMessageException(e.getMessage());
        }
    }

    /**
     * 直接发送邮件
     *
     * @param emailClient
     */
    public void send(EmailClient emailClient) {
        validate();
        emailClient.send(buildMimeMessage(emailClient));
    }
}
