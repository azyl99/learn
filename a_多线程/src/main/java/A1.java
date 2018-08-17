import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class A1 {

    private int value;
    private AtomicInteger value2;
    private AtomicReference<BigInteger> value3;

    public synchronized int getNext() {
        return value++;
    }

    public static void main(String[] args) {

    }
}
