package A_Semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author guya on 2019/2/2
 */
public class SemaphoreDemo {
    /**
     * 打饭窗口
     * 2    ：2个打饭窗口
     * true ：公平队列-FIFO
     */
    static Semaphore semaphore = new Semaphore(2, true);

    /**
     * <p>食堂打饭</p>
     *
     * @author hanchao 2018/3/31 21:13
     **/
    public static void main(String[] args) throws InterruptedException {
        //101班的学生
        Thread[] students101 = new Thread[5];
        for (int i = 0; i < 20; i++) {
            if (i < 10) {
                // 前10个同学都在耐心的等待打饭
                new Thread(new Student("打饭学生" + i, SemaphoreDemo.semaphore, 0)).start();
            } else if (i >= 10 && i < 15) {
                // 这5个学生没有耐心打饭
                new Thread(new Student("泡面学生" + i, SemaphoreDemo.semaphore, 1)).start();
            } else {
                students101[i - 15] = new Thread(new Student("聚餐学生" + i, SemaphoreDemo.semaphore, 2));
                students101[i - 15].start();
            }
        }
        //
        Thread.sleep(5000);
        for (int i = 0; i < 5; i++) {
            students101[i].interrupt();
        }
    }
}
