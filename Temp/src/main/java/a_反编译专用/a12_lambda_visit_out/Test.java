package a_反编译专用.a12_lambda_visit_out;

/**
 * @author guya on 2019/7/2
 */
public class Test {
    public interface Visitor {
        public void visit(String key);
    }

    public static void doSomething(String param, Visitor visitor) {
        visitor.visit(param);
    }
}
