import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author guya on 2019/1/15
 */
public class A_Collections_shuffle_并发问题 {

    private static Random random = new Random();

    private static String RANDOM_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static List<String> RANDOM_LETTERS = Arrays.asList(RANDOM_CHARS.split(""));

    /**
     * 有问题的实现
     *
     * @return
     */
    public static String generateNonceStr() {
        Collections.shuffle(RANDOM_LETTERS);
        String shuffled = String.join("", RANDOM_LETTERS);

        return shuffled.substring(0, 32); // 微信的随机字符串最大长度为32
    }

    /**
     * 正确的实现
     *
     * @return
     */
    public static String generateNonceStr_correct() {
        List<String> temp = new ArrayList<>(RANDOM_LETTERS);
        Collections.shuffle(temp);
        String shuffled = String.join("", temp);

        return shuffled.substring(0, 32); // 微信的随机字符串最大长度为32
    }

    /**
     * UUID 实现
     *
     * @return
     */
    public static String generateNonceStr_UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * ThreadLocal 实现
     *
     * @param args
     * @throws InterruptedException
     */
    private static final ThreadLocal<List<String>> randomLettersThreadLocal = ThreadLocal.withInitial(() -> Arrays.asList(RANDOM_CHARS.split("")));

    public static String generateNonceStr_ThreadLocal() {
        Collections.shuffle(randomLettersThreadLocal.get());
        String shuffled = String.join("", randomLettersThreadLocal.get());

        return shuffled.substring(0, 32); // 微信的随机字符串最大长度为32
    }


    static class MyRunnable implements Runnable {

        private int id;

        MyRunnable(int id) {
            this.id = id;
        }

        @Override
        public void run() {
//            System.out.println(id + " " + generateNonceStr());
//            System.out.println(id + " " + generateNonceStr_correct());
            System.out.println(id + " " + generateNonceStr_ThreadLocal());
        }
    }
    public static void main(String[] args) throws InterruptedException {
        long t1, t2;
        int THREAD_POOL_SIZE = 50;
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

//        ExecutorService pool = new ThreadPoolExecutor(5, 10, )

        t1 = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            pool.submit(new MyRunnable(i));
            generateNonceStr_ThreadLocal();
        }
        pool.shutdown();
        if (pool.awaitTermination(1000, TimeUnit.DAYS)) {
            t2 = System.currentTimeMillis();
            System.out.println("time:" + (t2 - t1));
            System.out.println(RANDOM_LETTERS);
            // main的threadLocal，和线程又不一样
            System.out.println(randomLettersThreadLocal.get());
        }
    }
}
