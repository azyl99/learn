package a_Id冲突;

import java.util.Random;

public class IdConfictTest {
    static Random random = new Random();

    private static final long BASE_NUM = 5L;
    private static final int ACTIVE_NUM = 4;

    public static void main(String[] args) {
//        BitMap bitMap = new BitMap(BASE_NUM);
//        AtomicInteger conflictCnt = new AtomicInteger(0);
//        for (int i = 0; i < ACTIVE_NUM; i++) {
//            long randomValue = random.nextLong() % BASE_NUM;
//            if (bitMap.getBit(randomValue)) {
//                conflictCnt.getAndIncrement();
//            }
//            bitMap.setBit(randomValue);
//        }
//        System.out.printf("get %d nums from %d, conflict:%d thoery_conflict_probitity:%f actual_conflict_probitity:%f",
//                ACTIVE_NUM, BASE_NUM, conflictCnt.get(), ACTIVE_NUM * 1.0 / BASE_NUM, conflictCnt.get() * 1.0 / ACTIVE_NUM);
    }

    BitMap bitMap = new BitMap(BASE_NUM);

    public void f(int m, int n, int k) {

    }
}

class BitMap {

    public static final Integer SIZE = Integer.SIZE;
    private long length;
    private int[] bitmap;

    public BitMap(long length) {
        this.length = length;
        int size = (int) (this.length / SIZE) + 1;
        this.bitmap = new int[size];
    }

    synchronized public boolean setBit(long pos) {
        if (pos < 0 || pos >= length) {
            return false;
        }
        int intPos = (int) (pos / SIZE);
        int bitPos = (int) (pos % SIZE);
        int bitValue = 1 << bitPos;
        int intvalue = bitmap[intPos] | bitValue;
        bitmap[intPos] = intvalue;
        return true;
    }

    synchronized public boolean getBit(long pos) {
        if (pos < 0 || pos >= length) {
            return false;
        }
        int intPos = (int) (pos / SIZE);
        int bitPos = (int) (pos % SIZE);
        int bitValue = 1 << bitPos;
        int result = bitmap[intPos] & bitValue;
        return result > 0;
    }
}
