package com.luckysweetheart.email.sender;

import com.luckysweetheart.email.client.EmailClient;
import com.luckysweetheart.email.message.EmailAttachment;
import com.luckysweetheart.email.message.EmailMessage;
import com.luckysweetheart.email.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.List;

import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

/**
 * 邮件发送实现
 * Created by yangxin on 2017/12/18.
 */
public class EmailSenderExecutor implements EmailSender {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private EmailClient emailClient;

    @Override
    public void send(EmailMessage emailMessageData) {
        long start = System.currentTimeMillis();
        logger.info("开始发送邮件");
        MimeMessage message = null;
        try {
            isTrue(emailMessageData.getTo() != null && emailMessageData.getTo().size() > 0, "收件人不能为空");
            notNull(emailMessageData.getSubject(), "邮件主题或标题不能为空");

            message = emailClient.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            List<EmailAttachment> emailAttachments = emailMessageData.getEmailAttachments();
            if (emailAttachments != null && emailAttachments.size() > 0) {
                for (EmailAttachment emailAttachment : emailAttachments) {
                    helper.addAttachment(emailAttachment.getName(), emailAttachment.createDataSource());
                }
            }

            helper.setFrom(emailClient.getUsername());
            String[] arr = new String[emailMessageData.getTo().size()];
            for (int i = 0; i < emailMessageData.getTo().size(); i++) {
                arr[i] = emailMessageData.getTo().get(i);
            }
            helper.setTo(arr);
            helper.setSubject(emailMessageData.getSubject());
            helper.setText(emailMessageData.getContent(), true);
            emailClient.send(message);

            long end = System.currentTimeMillis();
            logger.info("邮件发送成功 ，耗时 {} s", (end - start) / 1000);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public EmailClient getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(EmailClient emailClient) {
        this.emailClient = emailClient;
    }

}
