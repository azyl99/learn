import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author guya
 * @date 2018/8/30
 */
public class Test {

    List<String> values = new ArrayList<>();

    void add(String... args) {
        for (String arg : args) {
            values.add(arg);
        }
    }

    @Override
    public String toString() {
        return String.join(", ", values);
    }

    public static void test() {
        Test test = new Test();
        test.add("haha", "hehe", "huhu");
        System.out.println(test);
        String[] v = {"aa", "bb", "cc"};
        test.add(v);
        System.out.println(test);
    }

    public static void main(String[] args) {

        StringJoiner sj1 = new StringJoiner(",", "[", "]");
        List<Integer> arrayList1 = Arrays.asList(2, 3, 5, 7, 11);
        for (Integer integer : arrayList1) {
            sj1.add(integer.toString());
        }
        System.out.println(sj1);
        StringJoiner sj2 = new StringJoiner(",", "(", ")");
        List<Integer> arrayList2 = Arrays.asList(13, 17, 19);
        for (Integer integer : arrayList2) {
            sj2.add(integer.toString());
        }
        System.out.println(sj2);
        System.out.println(sj1.merge(sj2));
        System.out.println(sj2.merge(sj1));
    }
}

