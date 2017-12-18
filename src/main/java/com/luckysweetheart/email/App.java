package com.luckysweetheart.email;

import com.luckysweetheart.email.client.EmailClient;
import com.luckysweetheart.email.data.EmailMessageData;
import com.luckysweetheart.email.sender.EmailSender;
import com.luckysweetheart.email.util.FreeMarkerParseUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.MessagingException;
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

        EmailSender sender = applicationContext.getBean(EmailSender.class);

        Map<String,Object> map = new HashMap<>();
        map.put("a","yangxin");
        map.put("b","liujunyu");

        // 配置文件中 已配置了模板文件的路径，相当于classpath:/META-INF/template/test.ftl
        String content = FreeMarkerParseUtil.parse("test.ftl",map);

        EmailMessageData emailMessageData = EmailMessageData.create().content(content).subject("subject4").to("981987024@qq.com").attach("C:\\Users\\Developer5\\Desktop\\LuckDraw\\images\\bg.png");
        sender.sendEmailTemplate(emailMessageData);
    }

}
