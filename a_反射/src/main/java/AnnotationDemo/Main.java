package AnnotationDemo;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void test() {
        try {
            Class c = Class.forName("AnnotationDemo.Hello");
            // 判断这个类是否存在Description这样的一个注解
            boolean isExist = c.isAnnotationPresent(Description.class);
            if (isExist) {
                Description d = (Description)c.getAnnotation(Description.class);
                System.out.println(d.value());
            }
            Method[] ms = c.getMethods();
            for (Method m : ms) {
                Annotation[] as = m.getAnnotations();
                for (Annotation a: as) {
                    if (a instanceof Description) {
                        Description d = (Description) a;
                        System.out.println(d.value());
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        // 别人写的 Reflections 工具类，用来查找使用某个注解的类
        Reflections reflections = new Reflections(("com.annotation"));
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Description.class)
                .stream()
                .collect(Collectors.toSet());

        System.out.println(annotated.toString());

    }
    public static void main(String[] args) {
        test();
        Hello hello;
    }
}
