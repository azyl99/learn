package A_同类copy;

import org.springframework.beans.BeanUtils;

/**
 * @author guya
 * @date 2018/8/15
 */
public class ConverterA {

    public static A2 convert(A1 a1) {
        A2 a2 = new A2();
        BeanUtils.copyProperties(a1, a2);
        return a2;
    }

    public static void main(String[] args) {
        A1 a1 = new A1();
        a1.setName("guya");
        a1.setAge(21);
        A2 a2 = convert(a1);
        System.out.println(a2);
    }
}
