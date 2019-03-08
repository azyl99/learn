package com.example.myapplication1;

/**
 * @author guya on 2018/10/15
 */
public class Main {

    private static Main instance;

    public static Main newInstance() {
        if (instance == null) {
            return new Main();
        } else {
            return instance;
        }
    }

    @Value("zzz")
    private String name;

//    @Value("com.example.proxy2.helloServiceImpl")
//    private HelloService helloService;

    public void say() {
//        System.out.println("helloService: " + helloService);
        System.out.println("say name: " + name);
    }

    public static void main(String[] args) {
        new MyApplication(Main.class).run();
    }

}
