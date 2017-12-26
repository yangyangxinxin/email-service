package com.luckysweetheart.email.message;

import java.io.Serializable;
import java.util.List;

/**
 * 邮件消息
 * Created by yangxin on 2017/12/26.
 */
public interface EmailMessage extends Serializable {

    /**
     * 邮件内容
     * @return
     */
    String getContent();

    /**
     * 邮件收件人
     * @return
     */
    List<String> getTo();

    /**
     * 邮件主题
     * @return
     */
    String getSubject();

    /**
     * 邮件附件
     * @return
     */
    List<EmailAttachment> getEmailAttachments();
}
