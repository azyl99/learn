import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author guya on 2019/3/4
 */
public class A_ListSort {


    public static void main(String[] args) {
        List<A> list = Arrays.asList(
                new A(1, 1),
                new A(1, 2),
                new A(2, 2)
        );
        // 希望(a,b)降序
        list.sort((o1, o2) ->
                o1.a < o2.a ? 1 :
                        o1.a > o2.a ? -1 :
                                o1.b < o2.b ? 1 : -1);
        for (A a : list) {
            System.out.println(a.a + " " + a.b);
        }
    }


    static class A {
        int a;
        int b;

        public A(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
