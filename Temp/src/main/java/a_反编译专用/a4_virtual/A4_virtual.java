package a_反编译专用.a4_virtual;

/**
 * @author guya on 2019/1/23
 */
public class A4_virtual {

    public static void test(Person person) {
        System.out.println("test person");
        person.sleep();
        person.think();
    }

    public static void test(Student student) {
        System.out.println("test student");
        student.sleep();
    }

    public static void main(String[] args) {
        Student student = new Student("Zhang", 22);
        student.eat("apple");
        student.speak();
        student.sleep();
        student.think();

        Teacher teacher = new Teacher("Wang");
        teacher.eat("shit");
        teacher.speak();
        teacher.sleep();
        teacher.think();

        Person person = student;
        test(person);
    }
}
