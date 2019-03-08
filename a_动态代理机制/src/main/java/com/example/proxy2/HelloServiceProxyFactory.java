package com.example.proxy2;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author guya on 2018/10/12
 */
public class HelloServiceProxyFactory {

    public static HelloService getHelloServiceProxy(final HelloService helloService) {
        // 创建一个实现了 InvocationHandler 接口的匿名类的实例
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object args[]) throws Exception {
                System.out.println("before calling " + method); // 预处理
                System.out.println(proxy.getClass().getName());
                Object result = method.invoke(helloService, args);
                precessAnnotation(helloService.getClass());
                // 调用被代理的 HelloService 实例的方法
                System.out.println("after calling " + method); // 事后处理
                return result;
            }
        };
        Class classType = HelloService.class;
        return (HelloService) Proxy.newProxyInstance(
                classType.getClassLoader(), new Class[]{classType, Mouth.class}, handler);
    }

    public static void precessAnnotation(Class clazz) {
        Annotation[] annotations = clazz.getAnnotations();
        for(Annotation annotation : annotations){
            if(annotation instanceof Greet){
                Greet myAnnotation = (Greet) annotation;
                System.out.println("value: " + myAnnotation.value());
            }
        }
    }

    public static void main(String[] args) {
        HelloServiceProxyFactory.getHelloServiceProxy(new HelloServiceImpl()).greet();
    }

}
