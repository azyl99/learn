package a_静态块;

/**
 * @author guya on 2018/10/7
 */
public class A {

    // 静态块和静态变量的顺序就是代码顺序，这里i还未定义可以赋值（无效）但不能访问
    static {
        i = 1;
//        System.out.println("static: ", i);
    }

    static int i = 3;

    static {
        i = 2;
    }

    public static class B {
        static int i = 0;
        static B j = sTest();

        public static B sTest() {
            System.out.println("static func load!");
            return new B();
        }
    }

    public static void main(String[] args) {
        // 只用B.class并不会加载B
        System.out.println(B.class);
        System.out.println(A.i);
        // 只有用到B的静态实例时，B才会加载
        System.out.println(B.i);
    }
}
