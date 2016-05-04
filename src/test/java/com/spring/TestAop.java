package com.spring;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.proxy.test.UserLogin;
import com.spring.service.AService;

public class TestAop {
    private AService aserviceImpl;
    private UserLogin userLogin;

    @Before
    public void before() {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "classpath:config/applicationContext.xml" });
        // ApplicationContext context = new
        // FileSystemXmlApplicationContext("config/applicationContext.xml");
        // aserviceImpl = (AService) context.getBean("aService");
        userLogin = (UserLogin) context.getBean("userlogin");
    }

    // @Test
    // public void testAserviceImpl() {
    // aserviceImpl.barA();
    // }

    /**
     * 测试spring aop
     * 
     * @Description
     * @author gwb
     * @date 2016年3月15日 下午1:25:04
     */
    @Test
    public void testUserLogin() {
        userLogin.login("xxxxxxxxxxx");
        userLogin.gesName("userName");
    }
}
