package a_反编译专用.a11_enum;

/**
 * 本例与字节码无关，只是顺便放在这里，为了说明为什么模板类型E要extends MyEnum<E>
 *
 * @author guya on 2019/3/19
 */
public class MyEnum<E extends MyEnum<E>> implements Comparable<E> {

    @Override
    public final int compareTo(E o) {
        MyEnum<E> other = o;
        MyEnum<E> self = this;
        return 1;
    }

}
