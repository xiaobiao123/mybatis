package com.proxy.test;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class CheckUserAfter implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("用户 ----------------------" + returnValue + "----------------------登录前后处理");

    }

}