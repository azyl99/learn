import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author guya
 * @date 2018/8/21
 */
public class A_Counter {
    public static volatile int num = 0;
    // 使用CountDownLatch来等待计算线程执行完
    static CountDownLatch countDownLatch = new CountDownLatch(30);

    public static void test1() throws InterruptedException {
        // 开启30个线程进行累加操作
        for (int i = 0; i < 30; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        num++;//自加操作
                    }
                    countDownLatch.countDown();
                }
            }.start();
        }
        // 等待计算线程执行完
        countDownLatch.await();
        // 如果用volatile修饰的共享变量可以保证可见性，那么结果不应该是300000么?
        // 问题就出在num++这个操作上，因为num++不是个原子性的操作，而是个复合操作。
        System.out.println(num);
    }


    //使用原子操作类
    public static AtomicInteger num2 = new AtomicInteger(0);
    //使用CountDownLatch来等待计算线程执行完
    static CountDownLatch countDownLatch2 = new CountDownLatch(30);

    public static void test2() throws InterruptedException {
        //开启30个线程进行累加操作
        for (int i = 0; i < 30; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        num2.incrementAndGet();//原子性的num++,通过循环CAS方式
                    }
                    countDownLatch2.countDown();
                }
            }.start();
        }
        //等待计算线程执行完
        countDownLatch2.await();
        System.out.println(num2);
    }

    public static void main(String[] args) throws InterruptedException {
        test1();
        test2();
    }
}
