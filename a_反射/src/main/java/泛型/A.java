package 泛型;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guya on 2018/9/19
 */
public class A {
    public static void main(String[] args) throws NoSuchMethodException {
        List list = new ArrayList();
        list.add(1);
        list.add("abc");
        for (Object o : list) {
            System.out.println(o);
        }

        List<Integer> l2 = new ArrayList<>();
        l2.add(2);
        // 往 List<Integer> 里面添加 String 的方法：利用泛型
        try {
            Method m = l2.getClass().getDeclaredMethod("add", Object.class);
            m.invoke(l2, "hhh");
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        for (Object o : l2) {
            System.out.println(o);
        }
    }
}
