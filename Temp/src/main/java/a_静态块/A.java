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

    public static void main(String[] args) {
        System.out.println(A.i);
    }
}
