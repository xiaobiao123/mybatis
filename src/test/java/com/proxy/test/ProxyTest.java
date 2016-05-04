package com.proxy.test;

import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        UserLoginImpl user = new UserLoginImpl(); // 得到实例对象
        MyHandler handler = new MyHandler(user); // 将对象传入自己的处理器中

        UserLogin proxy = (UserLogin) Proxy.newProxyInstance(user.getClass().getClassLoader(), user.getClass()
                .getInterfaces(), handler); // 得到代理对象

        proxy.login("张三"); // 代理调用login方法
    }
}