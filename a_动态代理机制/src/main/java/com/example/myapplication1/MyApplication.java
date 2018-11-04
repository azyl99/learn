package com.example.myapplication1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author guya on 2018/10/15
 */
public class MyApplication {

    private Class<?> mainApplicationClass;

    public MyApplication(Class<?> mainApplicationClass) {// <T> void fuc(Class<T>)
        this.mainApplicationClass = mainApplicationClass;
    }

    public void run() {
        try {
            precessMainApplicationClass();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void precessMainApplicationClass() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Field[] fields = this.mainApplicationClass.getDeclaredFields();
        for (Field field : fields) {
            precessField(field, this.mainApplicationClass);
        }
    }

    private void precessField(Field field, Class<?> clazz) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, ClassNotFoundException {
        Annotation[] annotations = field.getAnnotations();
        for(Annotation annotation : annotations){
            if(annotation instanceof Value){
                Value myAnnotation = (Value) annotation;
                String value = myAnnotation.value();
                System.out.println("field:" + field + ", value: " + value);
                System.out.println(field.getType());

//                Class clazz0 = getClass().getClassLoader().loadClass(value);

                // getDeclaredConstructor(s)
                // getConstructor(s): 只返回制定参数类型访问权限是public的构造器
//                Constructor<?>[] constructors = clazz.getClass().getDeclaredConstructors();
//                System.out.println(constructors[0]);

                Method constructor = clazz.getMethod("newInstance");
                System.out.println(constructor);
                Object object = constructor.invoke(null);
                System.out.println(object);

                field.setAccessible(true);// private access
                field.set(object, value);

                Method method = clazz.getMethod("say");
                System.out.println(method);
                method.invoke(object);

//                Field field = clazz.getField("")

            }
        }
    }
}
