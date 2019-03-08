package com.example.proxy2;

/**
 * @author guya on 2018/10/10
 */
@Greet("My Greet!")
public class HelloServiceImpl implements HelloService, Mouth {

    @Override
    public void greet() {
        System.out.println("hello1");
    }

    @Override
    public void say(String s) {

    }
}
