package test;

/**
 * @author guya on 2018/12/10
 */
public class C implements Interface111, Interface21 {

    @Override
    public void f2() {

    }

    @Override
    public void f() {

    }

    private static void findAllInterfaces(Class<?> clazz) {
        findAllInterfaces(clazz, new StringBuilder());
    }

    public static void findAllInterfaces(Class<?> clazz, StringBuilder sb) {
        System.out.println(sb + clazz.getName());
        Class<?>[] ifcs = clazz.getInterfaces();
        for (Class<?> ifc : ifcs) {
            findAllInterfaces(ifc, new StringBuilder(sb).append("  "));
        }
    }

    public static void main(String[] args) {
        Class<?> clazz = C.class;
        // 只能找到直接继承的接口
        Class<?>[] ifcs = clazz.getInterfaces();
        for (Class<?> ifc : ifcs) {
            System.out.println(ifc.getName());
        }
        System.out.println();

        findAllInterfaces(clazz);
    }
}
