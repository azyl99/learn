package A_中断线程;


/**
 * 两种中断线程的方法
 * 通过interrupt方法可以中断处于阻塞状态的线程。
 *
 * 但是一般情况下不建议通过这种方式来中断线程，一般会在MyThread类中增加一个属性 isStop
 * 来标志是否结束while循环，然后再在while循环中判断isStop的值。
 *
 * stop方法已经是一个废弃的方法，它是一个不安全的方法。因为调用stop方法会直接终止run方法的调用，
 * 并且会抛出一个ThreadDeath错误，如果线程持有某个对象锁的话，会完全释放锁，导致对象状态不一致。
 */
public class Test {

    class MyThread extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (!isInterrupted() && i < Integer.MAX_VALUE) {
                i++;
            }
            System.out.println(i);
        }
    }

    class MyThread2 extends Thread{
        private volatile boolean isStop = false;
        @Override
        public void run() {
            int i = 0;
            while(!isStop){
                i++;
            }
            System.out.println(i);
        }

        public void setStop(boolean stop){
            this.isStop = stop;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        MyThread thread = test.new MyThread();
        thread.start();
        Thread.currentThread().sleep(20);
        thread.interrupt();

        MyThread2 thread2 = test.new MyThread2();
        thread2.start();
        Thread.currentThread().sleep(20);
        thread2.setStop(true);
    }
}
