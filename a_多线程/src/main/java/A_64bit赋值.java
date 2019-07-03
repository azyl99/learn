import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author guya on 2019/7/3
 */
public class A_64bit赋值 {

    private long a;
    public static final long LA = 0L;
    public static final long LB = -1L;

    private void write(long a) {
        while (true) {
            this.a = a;
        }
    }

    public void test() {
        ExecutorService esA = Executors.newFixedThreadPool(100, (r) ->new Thread(r, "writeThreadA"));
        ExecutorService esB = Executors.newFixedThreadPool(100, (r) ->new Thread(r, "writeThreadB"));
        esA.submit(()->write(LA));
        esB.submit(()->write(LB));
    }

    public static void main(String[] args) {
        A_64bit赋值 obj = new A_64bit赋值();
        obj.test();
        while (true) {
            long a = obj.a;
            if (a != LA && a != LB) {
                // 64位jvm不会有问题
                // 32位jvm会有输出
                System.out.println(a);
            }
        }
    }
}
