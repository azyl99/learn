package 最全的例子.demo2;

import org.springframework.core.ResolvableType;

import java.util.ArrayList;

/**
 * @author guya on 2018/12/11
 */
public class ResolvableTypeDemo {

    /**
     * resolvableType.as(type)：获取 resolvableType 继承的某个父类或接口的泛型参数类型
     */
    public static void as() {
        ResolvableType type = ResolvableType.forType(ExtendsList.class);
        System.out.println(type.getType());// 带泛型参数的类型
        System.out.println(type.getRawClass());// 纯类型， class 或者 interface

        ResolvableType listType1 = type.as(ArrayList.class);
        System.out.println(listType1.getType());                // ArrayList<Integer>
        System.out.println(listType1.getRawClass());            // ArrayList
        System.out.println(listType1.getGenerics()[0]);         // Integer

        ResolvableType listType2 = type.as(SI.class);
        System.out.println(listType2.getType());                // SI<String, Long>
        System.out.println(listType2.getRawClass());            // SI
        System.out.println(listType2.getGenerics()[0]);         // String
        System.out.println(listType2.getGenerics()[1]);         // Long
    }

    public static void main(String[] args) {
        as();
    }

    static class ExtendsList extends ArrayList<Integer> implements SI<String, Long> {
    }
}
