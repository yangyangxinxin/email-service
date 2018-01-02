package com.luckysweetheart.email.sender;

import com.luckysweetheart.email.client.EmailClient;
import com.luckysweetheart.email.exception.EmailMessageException;
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
    public void send(EmailMessage emailMessage) {
        long start = System.currentTimeMillis();
        logger.info("开始发送邮件");
        MimeMessage message = null;
        try {
            emailMessage.validate();

            logger.info(emailMessage.toString());

            message = emailClient.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // 附件
            List<EmailAttachment> emailAttachments = emailMessage.getEmailAttachments();
            if (emailAttachments != null && emailAttachments.size() > 0) {
                for (EmailAttachment emailAttachment : emailAttachments) {
                    helper.addAttachment(emailAttachment.getName(), emailAttachment.createDataSource());
                }
            }

            // 发件人，从配置中取
            helper.setFrom(emailClient.getUsername());

            // 收件人
            helper.setTo(emailMessage.getToArray());

            // 抄送
            String[] ccArray = emailMessage.getCcArray();
            if (ccArray != null) {
                helper.setCc(ccArray);
            }

            // 密送
            String[] bccArray = emailMessage.getBccArray();
            if (bccArray != null) {
                helper.setBcc(bccArray);
            }

            // 主题
            helper.setSubject(emailMessage.getSubject());

            // 邮件内容
            helper.setText(emailMessage.getContent(), true);

            // 发送
            emailClient.send(message);

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
