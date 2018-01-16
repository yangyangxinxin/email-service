package com.luckysweetheart.email.sender.demo;

import com.luckysweetheart.email.sender.client.EmailClient;
import com.luckysweetheart.email.sender.message.EmailMessage;
import com.luckysweetheart.email.sender.message.template.EmailTemplateMessage;
import com.luckysweetheart.email.sender.message.text.MarkDownMessage;
import com.luckysweetheart.email.sender.message.text.TextMessage;
import com.luckysweetheart.email.sender.client.EmailClient;
import com.luckysweetheart.email.sender.message.EmailMessage;
import com.luckysweetheart.email.sender.message.template.EmailTemplateMessage;
import com.luckysweetheart.email.sender.sender.EmailSender;
import com.luckysweetheart.email.sender.sender.EmailSenderExecutor;
import com.luckysweetheart.email.sender.template.freemarker.FreemarkerTemplate;
import com.luckysweetheart.email.sender.template.freemarker.FreemarkerTemplate;
import com.luckysweetheart.email.sender.sender.EmailSender;
import com.luckysweetheart.email.sender.sender.EmailSenderExecutor;
import com.luckysweetheart.email.sender.template.velocity.VelocityTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.MessagingException;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangxin on 2017/12/18.
 */
public class App {

    public static void main(String[] args) throws MessagingException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/spring-context.xml");
        EmailClient client = applicationContext.getBean(EmailClient.class);
        client.testConnection();

        EmailSender sender = applicationContext.getBean(EmailSenderExecutor.class);

        final Map<String, Object> map = new HashMap<>();
        map.put("a", "yangxin");
        map.put("b", "liujunyu");

        // 配置文件中 已配置了模板文件的路径，相当于classpath:/META-INF/template/test.ftl
        EmailMessage emailMessageDataFreemarker = EmailTemplateMessage
                .create()
                .template(new FreemarkerTemplate(DemoManger.TEST.getPath(), "a","yangxin","b","yangxin"))
                .subject("subject4")
                .to("981987024@qq.com")
                .attach("C:\\Users\\Developer5\\Desktop\\LuckDraw\\images\\bg.png")
                .cc("354394024@qq.com")
                .bcc("yangxin@ebaoquan.org");
        sender.send(emailMessageDataFreemarker);


        //EmailMessage emailMessageData = EmailTemplateMessage.create().template(new VelocityTemplate(DemoManger.VELOCITY.getPath(), map)).subject("subject4").to("981987024@qq.com,354394024@qq.com", ",").attach("C:\\Users\\Developer5\\Desktop\\LuckDraw\\images\\bg.png");
        //sender.send(emailMessageData);

       // EmailMessage message = MarkDownMessage.create().content("### hello world").subject("test").to("981987024@qq.com");
        //sender.send(message);

        // 纯文本内容发送
        //EmailMessage textMessage = TextMessage.create().content("13123123").to("981987024@qq.com").subject("纯文本消息");
        //sender.send(textMessage);
    }

}
