package com.luckysweetheart.email.message.text;

import com.luckysweetheart.email.message.EmailAttachment;
import com.luckysweetheart.email.message.EmailMessage;
import com.luckysweetheart.email.parser.executor.MarkDownParser;

import java.util.List;

/**
 * Created by yangxin on 2017/12/26.
 */
public class MarkDownMessage implements EmailMessage {

    /**
     * markdown 文件路径
     */
    private String path;

    /**
     * 收件人
     */
    private List<String> to;

    /**
     * 主题
     */
    private String subject;

    /**
     * 附件
     */
    private List<EmailAttachment> emailAttachments;

    public void setPath(String path) {
        this.path = path;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setEmailAttachments(List<EmailAttachment> emailAttachments) {
        this.emailAttachments = emailAttachments;
    }

    @Override
    public String getContent() {
        return new MarkDownParser().parse(this.path);
    }

    @Override
    public List<String> getTo() {
        return this.to;
    }

    @Override
    public String getSubject() {
        return this.subject;
    }

    @Override
    public List<EmailAttachment> getEmailAttachments() {
        return this.emailAttachments;
    }
}
