/**
 * 需要被代理的类 实现了一个接口Person
 *
 */
public class Student implements Person{

    @Override
    public void sayHello(String content, int age) {
        // TODO Auto-generated method stub
        System.out.println("student say hello" + content + " "+ age);
    }

    @Override
    public void sayGoodBye(boolean seeAgain, double time) {
        // TODO Auto-generated method stub
        System.out.println("student sayGoodBye " + time + " "+ seeAgain);
    }

}
