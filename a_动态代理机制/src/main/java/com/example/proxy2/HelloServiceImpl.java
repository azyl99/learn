package com.example.proxy2;

/**
 * @author guya on 2018/10/10
 */
@Greet("My Greet!")
public class HelloServiceImpl implements HelloService {

    @Override
    public void say() {
        System.out.println("hello1");
    }
}
