package a_反编译专用;

/**
 * @author guya on 2019/1/22
 */
public class A2_field {

    private int a = 1;

    private int b;

    static String s = "hhh";

    public static void main(String[] args) {
        long c;
        A2_field o = new A2_field();
        o.a = 2;
        c = 3;
        System.out.println(s + o.a + o.b + c);
    }
}
