package com.proxy.test;

import org.springframework.aop.AfterAdvice;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class BeforeAdviceTest {
    public static void main(String[] args) {
        UserLogin target = new UserLoginImpl(); // 具体的登录用户

        BeforeAdvice advice = new CheckUser(); // 前置通知

        AfterAdvice after = new CheckUserAfter();

        ProxyFactory proxyFactory = new ProxyFactory(); // Spring代理工厂

        proxyFactory.setTarget(target); // 设置代理目标

        proxyFactory.addAdvice(advice); // 为代理目标添加前置通知

        proxyFactory.addAdvice(after);

        UserLogin proxy = (UserLogin) proxyFactory.getProxy(); // 生成代理实例

        proxy.login("张三"); // 调用登录方法

        proxy.gesName("账户");
    }
}