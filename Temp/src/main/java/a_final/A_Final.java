package a_final;

/**
 * @author guya on 2019/1/17
 */
public class A_Final {

    Integer i;

    final static A_Final a = new A_Final(1);

    public A_Final(int i) {
        this.i = i;
    }

    public static void main(String[] args) {
        a.i = 2;
    }
}
