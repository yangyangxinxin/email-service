package com.luckysweetheart.email.sender.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.Set;

/**
 * Created by yangxin on 2017/12/29.
 */
public class RedisDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/spring-context.xml");

       /* RedisMessageListenerContainer container = applicationContext.getBean(RedisMessageListenerContainer.class);
        MessageListenerAdapter listenerAdapter = new MessageListenerAdapter();
        container.addMessageListener(listenerAdapter,new PatternTopic("email-service"));

        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
        redisTemplate.convertAndSend("email","");*/
        /*StringRedisTemplate template = applicationContext.getBean(StringRedisTemplate.class);
        Set<String> keys = template.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }*/
    }

}
