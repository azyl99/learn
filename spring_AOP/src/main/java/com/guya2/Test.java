package com.guya2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Test {
    //    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
        User user = (User) ctx.getBean("user");
        user.say();
        user.hello();
        user.hey("gmy");
        user.hehe();
        user.heihei();
    }

}