import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author guya on 2019/3/6
 */
public class A_ArrayList并发问题 {

    static ArrayList<Integer> list = new ArrayList<>();

    static class MyRunnable implements Runnable {

        int i;

        public MyRunnable(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            list.add(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int THREAD_POOL_SIZE = 50;
        int THREAD_NUMBER = 5000;
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        for (int i = 0; i < THREAD_NUMBER; i++) {
            pool.submit(new MyRunnable(i));
        }
        pool.shutdown();
        if (pool.awaitTermination(1000, TimeUnit.DAYS)) {
            for (int i = 0; i < list.size(); i++) {
                Integer value = list.get(i);
                if (value == null) {
                    System.out.println("ArrayList出现并发问题 " + i);
                }
            }
        }
    }
}
