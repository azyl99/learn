package a_反编译专用.a4_virtual;

/**
 * @author guya on 2019/1/23
 */
public interface Mouth {
    void speak();
    default void eat(String food) {
        System.out.println("Mouth eat " + food);
    }
}
