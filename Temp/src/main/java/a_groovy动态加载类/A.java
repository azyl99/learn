package a_groovy动态加载类;

import groovy.lang.GroovyClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author guya on 2018/12/14
 */
public class A {

    public static void main(String[] args) {
        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        Class clazz = groovyClassLoader.parseClass("class B { public static void f() { System.out.println(\"hhh\"); }}");
        try {
            Method method = clazz.getMethod("f");
            method.invoke(null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
