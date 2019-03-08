package a_反编译专用.a4_virtual;

/**
 * @author guya on 2019/1/23
 */
public class Person extends Animal {

    String name;

    public Person(String name) {
        this.name = name;
    }

    public void think() {
        System.out.println("[think] Person " + name);
    }

    @Override
    void sleep() {
        System.out.println("[sleep] Person " + name);
    }

    @Override
    public void speak() {
        System.out.println("[speak] Person " + name);
    }
}

