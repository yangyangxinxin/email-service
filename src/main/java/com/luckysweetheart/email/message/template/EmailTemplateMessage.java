package com.luckysweetheart.email.message.template;

import com.luckysweetheart.email.message.EmailAttachment;
import com.luckysweetheart.email.message.EmailMessage;
import com.luckysweetheart.email.template.Template;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 模板邮件消息
 * Created by yangxin on 2017/12/18.
 */
public class EmailTemplateMessage implements EmailMessage {

    /**
     * 模板
     */
    private Template template;

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

    private EmailTemplateMessage() {

    }

    public static EmailTemplateMessage create() {
        return new EmailTemplateMessage();
    }

    public EmailTemplateMessage template(Template template) {
        this.template = template;
        return this;
    }

    public EmailTemplateMessage subject(String subject) {
        this.subject = subject;
        return this;
    }

    public EmailTemplateMessage to(List<String> to) {
        if (this.to == null) {
            this.to = new ArrayList<String>();
        }
        this.to.addAll(to);
        return this;
    }

    public EmailTemplateMessage to(String... to) {
        if (this.to == null) {
            this.to = new ArrayList<String>();
        }
        if (to != null && to.length > 0) {
            List<String> strings = Arrays.asList(to);
            this.to.addAll(strings);
        }
        return this;
    }

    public EmailTemplateMessage to(String to, String separator) {
        if (StringUtils.isNotBlank(to)) {
            String[] strings = StringUtils.split(to, separator);
            return to(strings);
        }
        return this;
    }

    public EmailTemplateMessage attach(EmailAttachment... emailAttachments) {
        if (this.emailAttachments == null) {
            this.emailAttachments = new ArrayList<EmailAttachment>();
        }
        if (emailAttachments != null && emailAttachments.length > 0) {
            this.emailAttachments.addAll(Arrays.asList(emailAttachments));
        }
        return this;
    }

    public EmailTemplateMessage attach(byte[] contents, String name) {
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

    public EmailTemplateMessage attach(File... files) {
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

    public EmailTemplateMessage attach(String... path) {
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
        return template.getParser().parse(template);
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
