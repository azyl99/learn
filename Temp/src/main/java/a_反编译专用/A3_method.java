package a_反编译专用;

/**
 * @author guya on 2019/1/22
 */
public class A3_method { // static { cnt=10; } // 可以编译成功，但是无效
    static int cnt;
    int id;
    // 静态块，只有类加载的时候才会调用（jvm第一次加载class文件时）
    static {
        cnt += 10000;
        System.out.println("static block!");
    }
    // 普通块，每次 new 对象的时候都会调用。（这时候类已经加载了，所以cnt变量能使用）
    {// this.id = 5; // 可以编译成功，但是会被后面的构造函数覆盖
        cnt += 1000;
        System.out.println("non-static block!");
    }

    public A3_method(int id) {
        this.id = id;
    }

    public int say(int a) {
        return cnt + id + a;
    }

    public static int ssay(int a) {
        return cnt + a;
    }

    public static void main(String[] args) {
        A3_method o1 = new A3_method(1);
        System.out.println(o1.say(10));
        System.out.println(ssay(100));
        A3_method o2 = new A3_method(2);
    }
}
