package com.luckysweetheart.email;

import com.luckysweetheart.email.client.EmailClient;
import com.luckysweetheart.email.message.EmailMessage;
import com.luckysweetheart.email.message.template.EmailTemplateMessage;
import com.luckysweetheart.email.message.text.MarkDownMessage;
import com.luckysweetheart.email.message.text.TextMessage;
import com.luckysweetheart.email.template.freemarker.FreemarkerTemplate;
import com.luckysweetheart.email.sender.EmailSender;
import com.luckysweetheart.email.sender.EmailSenderExecutor;
import com.luckysweetheart.email.template.velocity.VelocityTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        //EmailTemplateMessage emailMessageData = EmailTemplateMessage.create().template(new FreemarkerTemplate(Demo.TEST.getPath(),map)).subject("subject4").to("981987024@qq.com").attach("C:\\Users\\Developer5\\Desktop\\LuckDraw\\images\\bg.png");

        //EmailTemplateMessage emailMessageData = EmailTemplateMessage.create().template(new VelocityTemplate(Demo.VELOCITY.getPath(),map)).subject("subject4").to("981987024@qq.com").attach("C:\\Users\\Developer5\\Desktop\\LuckDraw\\images\\bg.png");
        MarkDownMessage message = new MarkDownMessage();
        message.setPath("E:\\study\\code\\email-service\\Test.md");

        message.setSubject("test markdown");
        List<String> to = new ArrayList<>();
        to.add("981987024@qq.com");
        message.setTo(to);
        sender.send(message);

        // 纯文本内容发送
        TextMessage textMessage = new TextMessage();
        textMessage.setContent("aaaaaaa");
        textMessage.setSubject("测试纯文本内容");
        textMessage.setTo(to);

        sender.send(textMessage);
    }

}

enum Demo{

    TEST("test.ftl","TEST"),
    VELOCITY("/META-INF/template/test.vm","AAA")
    ;
    Demo(String path,String name){
        this.path = path;
        this.name = name;
    }
    private String path;

    private String name;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
