/**
 * @author guya
 * @date 2018/9/11
 */
public class TestGC {

    public Object instance;

    private final int _1MB = 1024*1024;

    private byte[] bigSize = new byte[_1MB];

    public static void main(String[] args) {
        TestGC objA = new TestGC();
        TestGC objB = new TestGC();
        objA.instance = objB;
        objB.instance = objA;

        System.gc();// 配置了 -verbose:gc 参数
    }
}
