package A_nio.my.sleepwait;

import lombok.extern.slf4j.Slf4j;

/**
 * "Thread-0" #11 prio=5 os_prio=0 tid=0x000000001e3b7800 nid=0x3610 in Object.wait() [0x000000001ecbe000]
 *    java.lang.Thread.State: WAITING (on object monitor)
 *         at java.lang.Object.wait(Native Method)
 *         - waiting on <0x000000076b9db7d8> (a java.lang.Class for A_nio.my.sleepwait.WaitDemo)
 *         at java.lang.Object.wait(Object.java:502)
 *         at A_nio.my.sleepwait.WaitDemo$ThreadA.run(WaitDemo.java:17)
 *         - locked <0x000000076b9db7d8> (a java.lang.Class for A_nio.my.sleepwait.WaitDemo)
 *
 * @author guya on 2019/4/16
 */
@Slf4j
public class WaitDemo {

    public static class MyThread extends Thread {

        int sleepTime;
        public MyThread(String name, int sleepTime) {
            super(name);
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            log.info("Thread start");
            synchronized (WaitDemo.class) {
                log.info("Thread enter");

                try {
                    Thread.sleep(sleepTime);
                    // 没有人唤醒
                    log.info("Thread wait start");
                    WaitDemo.class.wait();
                    log.info("Thread wait end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread threadA = new MyThread("ThreadA", 20000);
        MyThread threadB = new MyThread("ThreadB", 0);

//        threadA.start();
        threadB.start();
        threadA.start();
    }
}
