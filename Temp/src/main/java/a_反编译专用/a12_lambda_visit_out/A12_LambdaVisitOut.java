package a_反编译专用.a12_lambda_visit_out;

/**
 * @author guya on 2019/7/2
 */
public class A12_LambdaVisitOut {

    public static void main(String[] args) {
//        Integer a = 111;
//        Test.doSomething("222", (key)-> {
//            a = Integer.valueOf(key);// 不能修改
//        });

        Integer[] a = new Integer[]{111};
        Integer b = 1;
        Test.doSomething("222", (key) -> {
            a[0] = Integer.valueOf(key) + b;
        });
        System.out.println(a[0]);
    }
}
