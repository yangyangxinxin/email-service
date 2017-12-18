package com.luckysweetheart.email.client;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

/**
 * 邮件服务客户端。
 * Created by yangxin on 2017/12/18.
 */
public class EmailClient extends JavaMailSenderImpl {

    /**
     * 登录用户名,发件人
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 服务器地址
     */
    private String host;

    /**
     * 端口
     */
    private int port;

    /**
     * 协议
     */
    private String protocol;

    /**
     * 是否测试环境
     */
    private boolean debug;

    public EmailClient(String username, String password, String host, int port, String protocol, boolean debug) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
        this.protocol = protocol;
        this.debug = debug;
    }

    public EmailClient(String username, String password, String host, int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
        this.protocol = DEFAULT_PROTOCOL;
        this.debug = false;
    }

    /**
     * 初始化父类
     */
    public void init() {
        super.setDefaultEncoding("UTF-8");
        super.setHost(host);
        super.setPassword(password);
        super.setProtocol(protocol);
        super.setUsername(username);
        super.setPort(port);
        Properties props = new Properties();//②
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.auth", "true");
        javax.mail.Session session = javax.mail.Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        super.setSession(session);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String getProtocol() {
        return protocol;
    }

    @Override
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}