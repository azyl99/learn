package a_参数句柄修改;

/**
 * @author guya on 2019/1/17
 */
public class T {
    int i;
    T(int i) {
        this.i = i;
    }

    public static void tryModify(T t) {
        t = new T(3);
    }

    public static void main(String[] args) {
        T t = new T(1);
        tryModify(t);
        System.out.println(t.i);
    }

}
