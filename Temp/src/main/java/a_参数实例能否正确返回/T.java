package a_参数实例能否正确返回;

/**
 * @author guya on 2018/12/18
 */
public class T {

    // 参数值传过来的是句柄/指针值，不是对象本身 Person&
    public static A test(A a) {
        System.out.println("a:" + a.toString());
        // 返回的也是句柄/指针值
        return a;
    }

    public static void main(String[] args) {
        A a1 = new A(1, "a");
        A a2 = test(a1);
        System.out.println("a1:" + a1.toString());
        System.out.println("a2:" + a2.toString());
        System.out.println(a1.equals(a2));
    }

    static class A {
        int i;
        String s;

        public A(int i, String s) {
            this.i = i;
            this.s = s;
        }
    }
}
