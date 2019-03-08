package a_Id冲突;

import java.util.Random;

/**
 * @author guya on 2019/2/27
 */
public class TestRandom {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            System.out.println(random.nextLong());
        }
    }
}
