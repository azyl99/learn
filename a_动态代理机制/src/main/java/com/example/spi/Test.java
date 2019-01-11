package com.example.spi;

import java.util.ArrayList;
import java.util.ServiceLoader;

/**
 * SPI 全称为 (Service Provider Interface) ,是JDK内置的一种服务提供发现机制。
 * 参考：https://www.cnblogs.com/huzi007/p/6679215.html
 *
 * @author guya on 2019/1/4
 */
public class Test {
    public static void main(String[] args) {
        ServiceLoader<MyService> loaders = ServiceLoader.load(MyService.class);
        for (MyService myService : loaders) {
            myService.say();
        }
    }
}
