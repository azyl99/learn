package com.example.a2_invocation_handler.demo.pure;

/**
 * 纯净实现
 * 静态代理，这个代理类也必须要实现和被代理类相同的Person接口
 * 持有被代理对象的实例
 *
 */
public class PersonProxy implements Person {

    // 被代理的实现对象
    private Person person;

    public PersonProxy(Person person){
        this.person = person;
    }

    @Override
    public void sayHello(String content, int age) {
        System.out.println("PersonProxy sayHello begin");
        //在代理类的方法中 间接访问被代理对象的方法
        person.sayHello(content, age);
        System.out.println("PersonProxy sayHello end");
    }

    @Override
    public void sayGoodBye(boolean seeAgain, double time) {
        System.out.println("PersonProxy sayHello begin");
        //在代理类的方法中 间接访问被代理对象的方法
        person.sayGoodBye(seeAgain, time);
        System.out.println("PersonProxy sayHello end");
    }

    public static void main(String[] args) {
        //s为被代理的对象，某些情况下 我们不希望修改已有的代码，我们采用代理来间接访问
        PersonImpl s = new PersonImpl();
        //创建代理类对象
        PersonProxy proxy = new PersonProxy(s);
        //调用代理类对象的方法
        proxy.sayHello("welcome to java", 20);
        System.out.println("******");
        //调用代理类对象的方法
        proxy.sayGoodBye(true, 100);
    }

}
