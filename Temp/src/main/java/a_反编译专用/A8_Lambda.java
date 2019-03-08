package a_反编译专用;

/**
 * @author guya on 2019/2/18
 */
public class A8_Lambda {
    interface MathOperation {
        int operation(int a, int b);
    }

    public static void main(String[] args) {
        MathOperation addition = (int a, int b) -> a + b;
        System.out.println(addition.operation(1,2));
    }
}
