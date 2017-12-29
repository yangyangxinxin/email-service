# email-service
--- 
> 封装java发送邮件，简化开发流程。支持freemarker、velocity、markdown，支持附件。可以自己定义邮件模板类型。只需要实现`com.luckysweetheart.email.template.Template`接口，你需要做的就是实现你的模板解析方法。

> 尽管支持这么多模板引擎，还是建议使用freemarker模板。之前看到有人做过测试，freemarker的效率比其他模板都要高。

## start

> 配置：

```properties
spring.mail.username=your username
spring.mail.password=your password
spring.mail.host=your host
spring.mail.port=your port
spring.mail.protocol=your protocol
spring.mail.debug=true
# 如果你用的不是freemarker 模板 这项就不要指定了。
spring.mail.template.path=classpath:/META-INF/template/ 
```
> spring-context.xml:

```xml
<beans>
    <!--springUtil-->
    <bean id="springUtil" class="com.luckysweetheart.email.util.SpringUtil"/>
    
    <!--引入配置文件-->
    <context:property-placeholder location="classpath:/META-INF/config/app.properties" ignore-unresolvable="true"/>

    <!--初始化邮件客户端-->
    <bean class="com.luckysweetheart.email.client.EmailClient" id="emailClient" init-method="init">
        <constructor-arg name="username" value="${spring.mail.username}"/>
        <constructor-arg name="password" value="${spring.mail.password}"/>
        <constructor-arg name="host" value="${spring.mail.host}"/>
        <constructor-arg name="port" value="${spring.mail.port}"/>
        <constructor-arg name="protocol" value="${spring.mail.protocol}"/>
        <constructor-arg name="debug" value="${spring.mail.debug}"/>
    </bean>

    <!--freemarker解析器-->
    <bean id="freemarkerTemplateParser" class="com.luckysweetheart.email.parser.executor.FreemarkerTemplateParser">
        <property name="emailClient" ref="emailClient"/>
    </bean>
    
    <!--邮件发送执行者-->
    <bean class="com.luckysweetheart.email.sender.EmailSenderExecutor" id="emailSenderExecutor">
        <property name="emailClient" ref="emailClient"/>
    </bean>
    
    <!--freemarker模板配置-->
    <bean id="freeMarker" class="org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer">
        <property name="templateLoaderPath" value="${spring.mail.template.path}"/><!--指定模板文件目录-->
        <property name="freemarkerSettings"><!-- 设置FreeMarker环境属性-->
            <props>
                <prop key="template_update_delay">1800</prop><!--刷新模板的周期，单位为秒-->
                <prop key="default_encoding">UTF-8</prop><!--模板的编码格式 -->
                <prop key="locale">zh_CN</prop><!-- 本地化设置-->
            </props>
        </property>
    </bean>
    
    <!--velocity配置-->
    <bean class="org.apache.velocity.app.VelocityEngine" id="velocityEngine">
        <constructor-arg name="p">
            <props>
                <prop key="resource.loader">classpath</prop>
                <prop key="classpath.resource.loader.class">org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader</prop>
            </props>
        </constructor-arg>
    </bean>

    <bean id="velocityTemplateParser" class="com.luckysweetheart.email.parser.executor.VelocityTemplateParser">
        <property name="velocityEngine" ref="velocityEngine"/>
    </bean>
</beans>
```

> 发送邮件：

```java
public class App{
    public static void main(String[] args) throws MessagingException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/spring-context.xml");
        EmailClient client = applicationContext.getBean(EmailClient.class);
        
        // 测试邮件是否可以连接
        client.testConnection();

        EmailSender sender = applicationContext.getBean(EmailSender.class);

        // freemarker 变量
        Map<String,Object> map = new HashMap<>();
        map.put("a","yangxin");
        map.put("b","liujunyu");

        // freemarker 模板
        // 配置文件中 已配置了模板文件的路径，相当于classpath:/META-INF/template/test.ftl
        EmailTemplateMessage emailMessageData = EmailTemplateMessage.create().template(new FreemarkerTemplate(Demo.TEST.getPath(),map)).subject("subject4").to("981987024@qq.com").attach("C:\\Users\\Developer5\\Desktop\\LuckDraw\\images\\bg.png");
        sender.send(emailMessageData);
        
        // velocity 模板
        EmailTemplateMessage emailMessageData = EmailTemplateMessage.create().template(new VelocityTemplate(Demo.VELOCITY.getPath(),map)).subject("subject4").to("981987024@qq.com").attach("C:\\Users\\Developer5\\Desktop\\LuckDraw\\images\\bg.png");
        sender.send(emailMessageData);
        
        // markdown 文件        
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
```



