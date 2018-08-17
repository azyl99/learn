package 类的加载;

import java.lang.reflect.Method;

public class Temp {

    public void test() {
        try {
            Class c = this.getClass().getClassLoader().loadClass("类的加载.Person");
            System.out.println(c.getName());
            System.out.println("-----");
            Method[] methods = this.getClass().getClassLoader().loadClass("类的加载.Person").getMethods();
            for (Method m: methods) {
                System.out.println(m.toString());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Temp().test();
    }
}
