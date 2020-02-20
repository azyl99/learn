package guya.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author guya on 2019/12/4
 */
public class FunctionTest {

    private static void testFunction() {
        Function<Integer, Integer> square = (i) -> i * i;
        Function<Integer, Integer> doublee = (i) -> i + i;

        /**
         * 函数复合，g(f(x)) --> g.compose(f)
         */
        System.out.println(square.compose(doublee).apply(3));
        /**
         * 先 g(x) 再 f(x)
         */
        System.out.println(square.andThen(doublee).apply(3));
    }


    private static void testConsumer() {
        Consumer<Integer> print = (i) -> System.out.print(i + " ");

        List<Integer> arrayList = Arrays.asList(1, 2, 3, 4);
        arrayList.forEach(print);
        System.out.println();
    }

    private static void testBiFunction() {
        BiFunction<Integer, Integer, Integer> minus = (t, u) -> t - u;

        System.out.println(minus.apply(8, 2));
    }

    private static void testPredicate() {
        Predicate<Integer> isOdd = (i) -> (i & 1) == 1;
        Predicate<Integer> isNeg = (i) -> i < 0;

        System.out.println(isOdd.and(isNeg).test(3));
        System.out.println(isOdd.or(isNeg).test(3));

        Predicate<String> equalStrHello = Predicate.isEqual("hello");
        System.out.println(equalStrHello.test("hh"));
        System.out.println(equalStrHello.test("hello"));
    }

    public static void main(String[] args) {
        testFunction();
        testConsumer();
        testBiFunction();
        testPredicate();
    }
}
