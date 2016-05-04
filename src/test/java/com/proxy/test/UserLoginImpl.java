package com.proxy.test;

public class UserLoginImpl implements UserLogin {
    public String login(String userName) {
        System.out.println("欢迎 " + userName + " 登录系统");
        return "登录成功返回信息";
    }

    @Override
    public String gesName(String userName) {
        System.out.println(userName + "=xxxxxxxxxxxxxx");
        return userName;
    }
}