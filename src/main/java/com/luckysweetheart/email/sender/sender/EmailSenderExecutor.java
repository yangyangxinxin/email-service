package com.luckysweetheart.email.sender.sender;

import com.luckysweetheart.email.sender.client.EmailClient;
import com.luckysweetheart.email.sender.exception.EmailMessageException;
import com.luckysweetheart.email.sender.message.EmailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 邮件发送实现
 * Created by yangxin on 2017/12/18.
 */
public class EmailSenderExecutor implements EmailSender {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private EmailClient emailClient;

    @Override
    public void send(EmailMessage emailMessage) {
        long start = System.currentTimeMillis();
        logger.info("开始发送邮件");
        try {
            logger.info(emailMessage.toString());

            // 验证
            emailMessage.validate();
            // 发送
            emailClient.send(emailMessage.buildMimeMessage(emailClient));

            long end = System.currentTimeMillis();
            logger.info("邮件发送成功 ，耗时 {} s", (end - start) / 1000);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new EmailMessageException(e.getMessage());
        }
    }

    public EmailClient getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(EmailClient emailClient) {
        this.emailClient = emailClient;
    }

}
