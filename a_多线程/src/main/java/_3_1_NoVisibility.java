public class _3_1_NoVisibility {

    public static boolean ready;
    public static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                System.out.println("not ready");
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(2);
        number = 42;
        System.out.println("set number 42");
        ready = true;
        System.out.println("set ready true");
    }
}
