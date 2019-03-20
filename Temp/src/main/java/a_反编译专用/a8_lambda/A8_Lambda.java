package a_反编译专用.a8_lambda;

/**
 * @author guya on 2019/2/18
 */
public class A8_Lambda {

    public static void main(String[] args) {
        MathOperation addition = (int a, int b) -> a + b;
        System.out.println(addition.operation(1,2));
    }
}
