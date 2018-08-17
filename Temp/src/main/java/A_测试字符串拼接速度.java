public class A_测试字符串拼接速度 {

    private static final int SIZE1 = 1000000;
    private static final int SIZE2 = 10000;

    /**
     * StringBuilder拼接，线程不安全，速度快
     */
    public static void getStringBuilderTime() {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE1; i++) {
            sb.append(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("StringBuilder " + (end - start));
    }

    /**
     * StringBuffer拼接，线程安全，速度快
     */
    public static void getStringBufferTime() {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < SIZE1; i++) {
            sb.append(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("StringBuffer " + (end - start));
    }

    /**
     * String拼接，线程安全，速度最慢（10000条记录大概265毫秒）
     */
    public static void getStringTime() {
        long start = System.currentTimeMillis();
        String sb = new String();
        for (int i = 0; i < SIZE2; i++) {
            sb += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("String " + (end - start) * SIZE1 / SIZE2);
    }


    public static void getStringBuilderTime2() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE1; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.toString();
        }
        long end = System.currentTimeMillis();
        System.out.println("StringBuilder2 " + (end - start));
    }

    public static void getStringBufferTime2() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE1; i++) {
            StringBuffer sb = new StringBuffer();
            sb.append(i);
            sb.toString();
        }
        long end = System.currentTimeMillis();
        System.out.println("StringBuffer2 " + (end - start));
    }

    public static void main(String[] args) {
        getStringBuilderTime();
        getStringBufferTime();
        getStringTime();
        getStringBuilderTime2();
        getStringBufferTime2();
    }
}
