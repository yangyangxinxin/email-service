package com.luckysweetheart.email.message.text;

import com.luckysweetheart.email.message.EmailAttachment;
import com.luckysweetheart.email.message.EmailMessage;
import com.luckysweetheart.email.parser.executor.TextOnlyParser;

import java.util.List;

/**
 * Created by yangxin on 2017/12/26.
 */
public class TextMessage implements EmailMessage {

    /**
     * 文本内容
     */
    private String content;

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

    public void setContent(String content) {
        this.content = content;
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
        return new TextOnlyParser().parse(this.content);
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
