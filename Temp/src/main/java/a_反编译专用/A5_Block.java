package a_反编译专用;

/**
 * @author guya on 2019/1/30
 */
public class A5_Block {

    public static void main(String[] args) {
        try {
            Long a = Long.valueOf("");
        } catch (Exception e) {
            System.out.println("catch" + e);
        } finally {
            System.out.println("finally");
        }

        int sum = 0;
        for (int i = 0; i < 5; i++) {
            if (sum > 5) {
                break;
            }
            sum += i;
        }

        if (sum > 6) {
            System.out.println("if");
        } else {
            System.out.println("else");
        }
    }
}
