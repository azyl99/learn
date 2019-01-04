package 类的加载.a2;

/**
 * @author guya on 2018/12/9
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        // Class.forName() 要求JVM查找并加载指定的类，也就是说JVM会执行该类的静态代码段
        // 单单使用Class.forName( )是动态加载类是没有用的，其最终目的是为了实例化对象。
        // Class.forName( )静态方法的目的是为了动态加载类
        Class.forName("类的加载.a2.A");
    }
}

class A {
    static {
        System.out.println("load the class Interface1");
    }

}
