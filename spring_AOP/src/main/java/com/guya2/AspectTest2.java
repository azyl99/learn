package com.guya2;

/**
 * 通过XML将无注解的 AspectTest2 声明为切面
 */
public class AspectTest2 {

    public void beforeHehe() {
        System.out.println("beforeHehe");
    }

    public void afterHehe() {
        System.out.println("afterHehe");
    }

    public void beforeHeihei() {
        System.out.println("beforeHeihei");
    }

    public void afterHeihei() {
        System.out.println("afterHeihei");
    }
}
