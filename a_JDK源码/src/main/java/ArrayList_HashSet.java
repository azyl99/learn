import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author guya on 2019/1/11
 */
public class ArrayList_HashSet {

    public static void testString10() {
        String s = "1266674801";
        String st = "1";
        String s1 = "1266674800" + st;
        String s2 = "1518532210" + st;
        String s3 = "1521901170" + st;
        List<String> list = Arrays.asList("1266674801", "1518532211", "1521901171", "1522060761", "1522782591");
        int N = 50000000;
        long t1, t2;

        System.out.println("list(5)：句柄1/字符串1/字符串2/字符串3");
        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            list.contains(s);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            list.contains(s1);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            list.contains(s2);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            list.contains(s3);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
        System.out.println();

        Set<String> set = Sets.newHashSet("1266674801", "1518532211", "1521901171", "1522060761", "1522782591");

        System.out.println("set(5)：句柄1/字符串1/字符串2/字符串3");
        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            set.contains(s);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            set.contains(s1);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            set.contains(s2);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            set.contains(s3);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
        System.out.println();


        System.out.println("set(16)：句柄1/字符串1/字符串2/字符串3");
        Set<String> set2 = new HashSet<>();
        set2.addAll(list);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            set.contains(s);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            set2.contains(s1);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            set2.contains(s2);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            set2.contains(s3);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
    }

    public static void testInteger() {
        Integer s = 1266674801;
        Integer st = 1;
        Integer s1 = 1266674800 + st;
        Integer s2 = 1518532210 + st;
        Integer s3 = 1521901170 + st;
        Integer s4 = 1522060760 + st;
        List<Integer> list = Arrays.asList(1266674801, 1518532211, 1521901171, 1522060761, 1522782591);
        int N = 50000000;
        long t1, t2;

        System.out.println("list(5)：句柄1/整数1/整数2/整数3/整数4");
        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            list.contains(s);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            list.contains(s1);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            list.contains(s2);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            list.contains(s3);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            list.contains(s4);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
        System.out.println();

        Set<Integer> set = Sets.newHashSet(1266674801, 1518532211, 1521901171, 1522060761, 1522782591);

        System.out.println("set(5)：句柄1/整数1/整数2/整数3");
        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            set.contains(s);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            set.contains(s1);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            set.contains(s2);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            set.contains(s3);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
        System.out.println();


        System.out.println("set(16)：句柄1/整数1/整数2/整数3");
        Set<Integer> set2 = new HashSet<>();
        set2.addAll(list);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            set.contains(s);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            set2.contains(s1);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            set2.contains(s2);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);

        t1 = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            set2.contains(s3);
        }
        t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
    }

    public static void main(String[] args) {
        testInteger();
    }
}
