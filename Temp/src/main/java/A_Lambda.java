import java.util.Arrays;

/**
 * @author guya on 2019/1/20
 */
public class A_Lambda {
    public static void main(String[] args) {

        for (String string: Arrays.asList("aaaaa")) {
            System.out.println(string);
        }

        Arrays.asList("bbbbb").forEach(System.out::println);


//        String a = "abc";

//        Person person = () -> {
//            System.out.println(a);
//        };
//        person.think();
//
////        a = "123";
////        person.think();
////
////        System.out.println();
    }

}

//interface Person {
//    void think();
//}
