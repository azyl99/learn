package A_同步器;

import java.util.concurrent.locks.Lock;

/**
 * @author guya on 2018/9/18
 */
public class TwinsLockTest {

    public static void main(String[] args) {
        final Lock lock = new TwinsLock();

        class Worker extends Thread {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100L);
                    } catch (Exception ex) {
                    }
                    lock.lock();
                    try {
                        Thread.sleep(500L);
                        System.out.println(Thread.currentThread());
                        Thread.sleep(500L);
                    } catch (Exception ex) {
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.start();
        }

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(200L);
                    System.out.println();
                } catch (Exception ex) {

                }
            }
        }).start();// main会等待所有子线程结束：Java进程确定虚拟机中没有线程运行的时候，退出进程。
    }
}