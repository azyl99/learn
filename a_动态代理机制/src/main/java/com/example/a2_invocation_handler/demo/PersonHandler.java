package com.example.a2_invocation_handler.demo;

import com.example.a2_invocation_handler.demo.pure.Person;
import com.example.a2_invocation_handler.demo.pure.PersonImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 和前面的纯净实现差不多，只不过纯净实现需要每个方法都分别加切面，而 InvocationHandler 不需要
 *
 * 动态代理,动态代理类不要显示的实现被代理类所实现的接口
 */
public class PersonHandler implements InvocationHandler {

    private Object object;

    public PersonHandler(Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("PersonHandler invoke begin");
        System.out.println("proxy: "+ proxy.getClass().getName());
        System.out.println("method: "+ method.getName());
        for(Object o : args){
            System.out.println("arg: "+ o);
        }
        //通过反射调用 被代理类的方法
        method.invoke(object, args);
        System.out.println("PersonHandler invoke end\n");
        return null;
    }

    public static void main(String [] args){

        // 这一句是生成代理类的class文件，前提是你需要在工程根目录下创建com/sun/proxy目录，不然会报找不到路径的io异常
        // 如果不想创建文件夹，可以选择不生成
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        //创建需要被代理的类
        PersonImpl s = new PersonImpl();

        //获得加载被代理类的 类加载器
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        // 指明被代理类实现的接口
        Class<?>[] interfaces = s.getClass().getInterfaces();
        System.out.println(interfaces.length + " " + interfaces[0]);
        System.out.println();

        // 创建被代理类的委托类,之后想要调用被代理类的方法时，都会委托给这个类的invoke(Object proxy, Method method, Object[] args)方法
        PersonHandler handler = new PersonHandler(s);
        //生成代理类
        Person proxy = (Person) Proxy.newProxyInstance(loader, interfaces, handler);
        //通过代理类调用 被代理类的方法
        proxy.sayHello("yujie.wang", 20);
        proxy.sayGoodBye(true, 100);
        System.out.println("end");
    }

}
