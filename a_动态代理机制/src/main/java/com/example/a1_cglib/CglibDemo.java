package com.example.a1_cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

//import org.springframework.cglib.proxy.Enhancer;

/**
 * @author guya on 2019/3/28
 */
public class CglibDemo {
    public void sayHello() {
        System.out.println("hello world");
    }

    public static void main(String[] args) {
        // 该设置用于输出jdk动态代理产生的类
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 该设置用于输出jdk动态代理产生的类 在工程根目录下，按包组织的结构
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, ".");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibDemo.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before method run...");
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("after method run...");
                return result;
            }
        });
        CglibDemo proxy = (CglibDemo) enhancer.create();
        proxy.sayHello();
    }
}
