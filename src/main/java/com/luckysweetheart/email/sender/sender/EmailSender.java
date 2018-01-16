package com.luckysweetheart.email.sender.sender;


import com.luckysweetheart.email.sender.message.EmailMessage;

/**
 * 邮件发送者
 * Created by yangxin on 2017/12/26.
 */
public interface EmailSender {

    /**
     * 发送邮件
     * @param
     */
    void send(EmailMessage emailMessage);

}
