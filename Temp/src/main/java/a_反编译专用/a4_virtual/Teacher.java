package a_反编译专用.a4_virtual;

/**
 * @author guya on 2019/1/23
 */
public class Teacher extends Person {

    public Teacher(String name) {
        super(name);
    }

    @Override
    public void think() {
        System.out.println("[think] Teacher " + name);
    }

    @Override
    void sleep() {
        System.out.println("[sleep] Teacher " + name);
    }
}
