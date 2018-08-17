import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

@Setter
@Getter
@Slf4j
public class A1 {

    int value;

    public static void main(String[] args) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        Map<String, String> map = new WeakHashMap<>();
        map.put("a", "a1");
        map.put("b", "b2");
        Object object;
        Integer i1 = new Integer(222);
        Integer i2 = 222;
        Integer i3 = 222;
        System.out.println(i1 == i2);
        System.out.println(i1 == i3);
        System.out.println(i2 == i3);
        Integer i4 = new Integer(122);//  Integer 缓存策略仅在自动装箱（autoboxing）的时候有用，使用构造器创建的 Integer 对象不能被缓存。
        Integer i5 = 122;
        Integer i6 = 122;
        System.out.println(i4 == i5);
        System.out.println(i4 == i6);
        System.out.println(i5 == i6);

        String[] x = "aaa".split(",");
        System.out.println(x.length);
//        Long.parseLong("");// 会报错

        A1 a1 = new A1();
        a1.setValue(1);
        List<A1> list = new ArrayList<>();
        list.add(a1);
        a1.setValue(5);// 即便已经放入List了，a1仍然指向原来的实例，依然可以修改值
        System.out.println(list.get(0).getValue());

        System.out.println('\\');

        String str = "hello";
        StringBuilder sbuf = new StringBuilder();
        sbuf.append("abc").append(str, 1, 3);
        System.out.println(sbuf.toString());
        log.info("hello, {}", "zyl");

        System.out.println(str instanceof String);
        Long a = (Long)null;

        ByteBuffer byteBuffer = ByteBuffer.allocate(49);
    }
}
