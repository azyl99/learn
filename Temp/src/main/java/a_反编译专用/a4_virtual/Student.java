package a_反编译专用.a4_virtual;

/**
 * @author guya on 2019/1/23
 */
public class Student extends Person {

    int stuNo;

    public Student(String name, int stuNo) {
        super(name);
        this.stuNo = stuNo;
    }

    @Override// 这个@Override去掉也没关系
    public void think() {
        System.out.println("[think] Student " + name + stuNo);
    }

    @Override
    void sleep() {
        System.out.println("[sleep] Student " + name + stuNo);
    }
}
