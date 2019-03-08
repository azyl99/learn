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
    // RuntimeException不需要强制检查
    public void testThrow1() {
        throw new RuntimeException("hhh");
    }
    public void testThrow2() throws Exception {
        throw new Exception("hhh");
    }

    public static void howTo() {
        // WeakHashMap, map.put
        Map<String, String> map = new WeakHashMap<>();
        map.put("a", "a1");
        map.put("b", "b2");

        A1 a1 = new A1();
        a1.setValue(1);
        List<A1> list = new ArrayList<>();
        list.add(a1);
        a1.setValue(5);// 即便已经放入List了，a1仍然指向原来的实例，依然可以修改值
        System.out.println(list.get(0).getValue());

        // 无符号右移
        System.out.printf("0x%x >>> 1  =>  0x%x\n", -1, -1 >>> 1);

    }

    public static void see() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        Object object;
        Long a = (Long) null;
        ByteBuffer byteBuffer = ByteBuffer.allocate(49);
        System.out.println('\\');// 字符也要转义
//        Long.parseLong("");// 会报错
    }
    
    public static void test1() {
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

        System.out.println();
        System.out.println(222 == i1);// true 自动包裹和解包裹
        System.out.println(i1 == 222);// true 自动包裹和解包裹
    }

    public static void test2() {
        // arraycopy
        int[] arr1 = {1, 4, 7, 10}, arr2 = new int[3];
        System.arraycopy(arr1, 0, arr2, 0, arr1.length - 1);
        for (int val : arr2) {
            System.out.printf("%d ", val);
        }
        System.out.println();

        String[] x = "aaa".split(",");
        System.out.println(x.length);

        String str = "hello";
        StringBuilder sbuf = new StringBuilder();
        sbuf.append("abc").append(str, 1, 3);
        System.out.println(sbuf.toString());
        log.info("hello, {}", "zyl");// Slf4j日志
        System.out.println(str instanceof String);

        System.out.println("abcdecdf".lastIndexOf("cd"));// 返回字符串最后一次出现的位置
        System.out.println("abcdecdf".lastIndexOf("cd", 4));// 返回字符串最后一次出现的位置，需要比4小
//        System.out.println(Integer.parseInt("2147483648"));
        System.out.println(Integer.parseInt("-2147483648"));
    }

    public static void main(String[] args) {
//        howTo();
//        test1();
//        test2();
//        see();
        Integer a = null;
//        System.out.println(a.equals(null));// NullPointerException

        B.main(null);
    }
}

class B {
    private int x;      // only for self
    protected int y;    // package
    public int z;       // for all
    int t;              // default <=> package + 子类

    public static void main(String[] args) {
        System.out.println("Mouth am not a public class: B");
    }

    @Override
    public String toString() {
        return "toString B";
    }
}

class C extends B {
    public static void main(String[] args) {
        B b = new B();
        b.y = 0;
        b.z = 0;
        b.t = 0;
//        b.clone();// 'clone()' has protected access in 'java.lang.Object'
        B obj = new C();
        System.out.println(obj.toString());
    }

    @Override
    public String toString() {
        return "toString C";
    }
}


enum Size {
    SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");

    private Size(String abbreviation) { this.abbreviation = abbreviation; }
    public String getAbbreviation() { return abbreviation; }

    private String abbreviation;
    public static void main(String[] args)
    {
        Size size = Enum.valueOf(Size.class, "LARGE");// 厉害了，直接解析出来
        System.out.println("size=" + size);
        System.out.println("abbreviation=" + size.getAbbreviation());
        System.out.println("ordinal=" + size.ordinal());
    }
}
