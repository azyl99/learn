import java.util.BitSet;

/**
 * @author guya
 * @date 2018/9/4
 */
public class A_ArrayDeque {

    static final int MIN_INITIAL_CAPACITY = 16;

    /**
     * ArrayDeque确定容量的一段
     * @param numElements
     * @return
     */
    private static int calculateSize(int numElements) {
        int initialCapacity = MIN_INITIAL_CAPACITY;
        // Find the best power of two to hold elements.
        // Tests "<=" because arrays aren't kept full.
        if (numElements >= initialCapacity) {
            initialCapacity = numElements;
            // 无符号左移，不是循环左移！
            // 一波操作下来，有效位填满了1。00010100 -> 00011111
            // 加1之后就变成了00100000
            initialCapacity |= (initialCapacity >>> 1);
            initialCapacity |= (initialCapacity >>> 2);
            initialCapacity |= (initialCapacity >>> 4);
            initialCapacity |= (initialCapacity >>> 8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;

            if (initialCapacity < 0)   // Too many elements, must back off
                initialCapacity >>>= 1;// Good luck allocating 2 ^ 30 elements
        }
        return initialCapacity;
    }

    // 自己写的
    public static String toBinaryString(int num) {
        StringBuffer sb = new StringBuffer();
        int mask = 1;
        while (num != 0) {
            sb.append((num & mask) == 0 ? 0 : 1);
            num >>>= 1;
        }
        sb.reverse();
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(calculateSize(1));
        System.out.println(calculateSize(10));
        System.out.println(calculateSize(100));
        System.out.println(calculateSize(17));
//        BitSet bitSet = new BitSet(5);
//        bitSet.set(1);
//        bitSet.set(3);
//        System.out.println(bitSet);
        System.out.println(toBinaryString(19));
    }
}
