package com.proxy.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeforeAdviceTest1 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "classpath:config/applicationContext.xml" });
        UserLogin ul = (UserLogin) context.getBean("userlogin");
        ul.login("张三");
    }
}