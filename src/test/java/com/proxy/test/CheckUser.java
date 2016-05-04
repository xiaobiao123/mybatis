package com.proxy.test;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class CheckUser implements MethodBeforeAdvice {

    public void before(Method method, Object[] objs, Object obj) throws Throwable {
        String userName = (String) objs[0]; // 获取登录名
        System.out.println("用户 ---------------------" + userName + "-------------------登录前处理");
    }

}