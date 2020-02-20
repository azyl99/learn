import lombok.extern.slf4j.Slf4j;

/**
 * @author guya on 2018/9/22
 */
@Slf4j
public class TestException extends RuntimeException {
    private int code;

    public TestException() {

    }

    public TestException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void test() {
        throw new TestException();
    }

    public static void main(String[] args) {
//        TestException testException = new TestException("2",1);
//        try {
//            testException.test();
//        } catch (Exception e) {
//            log.error("hhh");
//            log.error("xxx",e);
//        }
        log.info("hhh");
        String a = null;
        System.out.println(".".equalsIgnoreCase(a));
    }
}
