package a_反编译专用;

/**
 * @author guya on 2019/1/30
 */
public class A6_innerClass {

    private int a;
    private static int b;
    Inner inner = new Inner();

    static class SInner {
        int i;

        public void test() {
            // 不可以访问外部类的非静态变量
            // Non-static field 'a' cannot be referenced from a static context
            // a = 1;
            b = 22;
        }
    }

    class Inner {
        int i;
        public void test() {
            // 可以访问外部类的私有变量
            a = 11;
            b = 33;  inner.i = 2;
        }
        // Inner classes cannot have static declarations
        // public static void test() {}
    }

    public static void main(String[] args) {
        A6_innerClass.SInner sInner = new A6_innerClass.SInner();
        sInner.i = 2;

        A6_innerClass a6_innerClass = new A6_innerClass();
        a6_innerClass.inner.i = 3;
        a6_innerClass.inner.test();
    }
}
