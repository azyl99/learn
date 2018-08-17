package A_synchronized;

public class SynchronizedTest {

    /**
     * 锁住class对象
     */
    public void synchronizedClass() {
        synchronized (SynchronizedTest.class) {
            System.out.println("synchronizedClass");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 锁住方法，lock的是this
     */
    public synchronized void synchronizedMethod() {
        System.out.println("synchronizedMethod");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 锁住静态方法，lock的是class
     */
    public static synchronized void synchronizedStaticMethod() {
        System.out.println("synchronizedStaticMethod");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 不会有影响，正常调用
     */
    public void synchronizedMethod2WithNosynchronized() {
        System.out.println("synchronizedMethod2WithNosynchronized  no");
    }

    /**
     * synchronizedMethod 已经锁住实例， 再加锁不成功
     */
    public void synchronizedThis1() {
        synchronized (SynchronizedTest.this) {
            System.out.println("synchronizedThis1");
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * synchronizedMethod 已经锁住实例， 再加锁不成功
     */
    public void synchronizedThis2() {
        synchronized (this) {
            System.out.println("synchronizedThis2");
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

//        https://blog.csdn.net/haha_321/article/details/53308948

//        test1();
//        test2();
//        test3();
//        test4();
        test5();
    }

    /**
     * Java语法规定，任何线程执行同步方法、同步代码块之前，必须先获取对应的监视器。并且 监听器this 和 **.class 是不同的
     * this 是对用方法的对象本身 class 是该类本身(只有监听器相同锁才会起作用)
     */
    private static void test1() {
        final SynchronizedTest t = new SynchronizedTest();
        //调用代码看下面
        Thread th = new Thread(new Runnable() {

            @Override
            public void run() {
                t.synchronizedClass();
            }

        });
        th.start();
        th = new Thread(new Runnable() {

            @Override
            public void run() {
                t.synchronizedThis1();
            }

        });
        th.start();
        t.synchronizedMethod2WithNosynchronized();
    }

    /**
     * 因为 static 方法 和 class 一样都是锁的该类本身 是同一个监听器
     */
    private static void test2() {
        final SynchronizedTest t = new SynchronizedTest();
        Thread th = new Thread(new Runnable() {

            @Override
            public void run() {
                t.synchronizedClass();
            }

        });
        th.start();
        th = new Thread(new Runnable() {

            @Override
            public void run() {
                t.synchronizedStaticMethod();
            }

        });
        th.start();
    }

    /**
     * 这个由于二者锁的是同一个对象 一个执行完另一个才会执行（尽管是调用的不同的方法）
     */
    private static void test3() {
        final SynchronizedTest t = new SynchronizedTest();
        Thread th = new Thread(new Runnable() {

            @Override
            public void run() {
                t.synchronizedThis1();
            }

        });
        th.start();
        t.synchronizedMethod2WithNosynchronized();
        th = new Thread(new Runnable() {

            @Override
            public void run() {
                t.synchronizedThis2();
            }

        });
        th.start();
    }

    /**
     * 这组一个是this 一个是 非static 方法（但是调用方法的对象是同一个）
     * 这组这个非静态方法 也是所得当前对象 所以一个执行完另一个才会执行（如果是不同的对象 如把这组的第二个 换成t1，结果就会各自执行）
     */
    private static void test4() {
        final SynchronizedTest t = new SynchronizedTest();
        final SynchronizedTest t1 = new SynchronizedTest();
        Thread th = new Thread(new Runnable() {

            @Override
            public void run() {
                t.synchronizedThis1();
            }

        });
        th.start();
        t.synchronizedMethod2WithNosynchronized();

        th = new Thread(new Runnable() {

            @Override
            public void run() {
//                t1.synchronizedMethod();
                t.synchronizedMethod();
            }

        });
        th.start();

    }

    private static void test5() {
        final SynchronizedTest t = new SynchronizedTest();
        final SynchronizedTest t1 = new SynchronizedTest();
        Thread 	th = new Thread(new Runnable() {

            @Override
            public void run() {
                t.synchronizedThis1();
            }

        });
        th.start();

        t.synchronizedMethod2WithNosynchronized();

        th = new Thread(new Runnable() {

            @Override
            public void run() {
                t1.synchronizedThis2();
            }

        });
        th.start();
    }
}
