package com.guya2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectTest1 {

    /**
     * 方法一：复用切点表达式
     */
    @Pointcut("execution(* *.say(..))")
    public void say(){
        System.out.println("好像这个函数里的内容没有用，只是做个标识而已");
    }
    @Before("say()")// Before通知
    public void beforeSay(){
        System.out.println("beforeSay");
    }
    @After("say()")
    public void afterSay(){
        System.out.println("afterSay");
    }

    /**
     * 方法二：直接使用切点表达式
     */
    @Before("execution(* *.hello(..))")
    public void before1Hello() {
        System.out.println("before1Hello");
    }
    @Before("execution(* *.hello(..))")// 可以插入多个Before
    public void before2Hello() {
        System.out.println("before2Hello");
    }
    @After("execution(* *.hello(..))")
    public void afterHello() {
        System.out.println("afterHello");
    }

    /**
     * 方法二：直接使用切点表达式
     */
    @Around("execution(* *.hey(..))")
    public void aroundHey(ProceedingJoinPoint jp) {
        try {
            System.out.println("beforeHey");
            Object[] arguments = jp.getArgs();
            if (arguments != null) {
                for (Object argument : arguments) {
                    System.out.println("guest is " + (String)argument);
                }
            }
            jp.proceed();// 注释掉这句会阻塞原先方法的调用
            System.out.println("afterHey");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
