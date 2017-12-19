# email-service
--- 
> 封装java发送邮件，简化开发流程。支持freemarker模板邮件、邮件附件支持。

## start

> 配置：

```properties
spring.mail.username=your username
spring.mail.password=your password
spring.mail.host=your host
spring.mail.port=your port
spring.mail.protocol=your protocol
spring.mail.debug=true
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

    <!--发送邮件配置-->
    <bean class="com.luckysweetheart.email.sender.EmailSender" id="emailSender">
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

        // 配置文件中 已配置了模板文件的路径，相当于classpath:/META-INF/template/test.ftl
        String content = FreeMarkerParseUtil.parse("test.ftl",map);

        // 设置邮件内容及附件。
        EmailMessageData emailMessageData = EmailMessageData.create().content(content).subject("subject4").to("981987024@qq.com").attach("C:\\Users\\Developer5\\Desktop\\LuckDraw\\images\\bg.png");
        sender.sendEmailTemplate(emailMessageData);
    }
}
```



