/**
 * @author guya on 2018/12/14
 */
public class A_Boolean {
    public static void main(String[] args) {
        Boolean f = null;
        System.out.println(Boolean.valueOf(null));  // 可以，Boolean.valueOf(String) -> parseBoolean(String) -> if (s != null)
        System.out.println(Boolean.valueOf(f));     // 不可以，Boolean.valueOf(boolean b) -> (Boolean 拆箱成 boolean 时 NullPointerException)
    }
}
