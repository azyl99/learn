package a_反编译专用;

/**
 * @author guya on 2019/2/19
 */
public class A9_AnonymousInnerClass {

    public static void main(String[] args) {
        // 等价于 ((I) (s) -> System.out.println(s)).say();

        new Mouth() {

            @Override
            public void say(String s) {
                System.out.println(s);
            }
        }.say("hhh");
    }

    public interface Mouth {
        void say(String s);
    }
}
