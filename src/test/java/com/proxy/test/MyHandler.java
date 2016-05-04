package com.proxy.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyHandler implements InvocationHandler {
    private Object obj;

    public MyHandler(Object obj) {
        this.obj = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        beforeLogin(); // 登录前处理，更具自己需要来写

        Object result = method.invoke(obj, args); // 调用真正的方法

        afterLogin(); // 登录后处理，更具自己需要来写

        return result;
    }

    public void beforeLogin() {
        System.out.println("登录前处理");
    }

    public void afterLogin() {
        System.out.println("登录后处理");
    }
}