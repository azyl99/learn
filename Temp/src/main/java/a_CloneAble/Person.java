package a_CloneAble;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Person implements Cloneable {

    // String的特殊性在于：
    //    因为他为引用型，而且他指向的值为常量，克隆出来的对象改变他的值。实际上是改变了克隆出来对象String类型成员的指向，不会影响被克隆对象的值及其指向。
    // 这告诉我们支持更方便实现克隆的一种途径：将自己定义的类编写为不可更改。
    String name;
    Integer age;
    Car car;

    public Person(String name, Integer age, Car car) {
        this.name = name;
        this.age = age;
        this.car = car;
    }

    public void setCarName(String name) {
        car.setName(name);
    }

    public void setCarPrice(Integer price) {
        car.setPrice(price);
    }

    public String toString() {
        return name + " " + age + " " + car.name + " " + car.price;
    }

    /**
     * Object类的clone方法是protected，需要覆盖为public
     * @return Object
     */
    @Override
    protected Object clone() {
        Person obj = new Person(name, age, car);
        return (Object)obj;
    }

    public static void main(String[] args) {
        Person person1 = new Person("zyl", 21, new Car("Fort", 100000));
        Person person2 = (Person) person1.clone();
        person2.setName("gmy");  // String是不可修改的实例，这一步改变了a2.name的句柄
        person2.setAge(22);
        person2.setCar(new Car("QQ", 200000));// a2.car的句柄改为指向new出来的这个实例。
        System.out.println(person1.toString());
        System.out.println(person2.toString());

        Person person3 = new Person("zyl", 21, new Car("Fort", 300000));
        Person person4 = (Person) person3.clone();
        person4.setName("gmy");
        person4.setAge(22);
        person4.setCarName("DAZ");// a4.car和a3.car指向同一个Car实例，但内容却被改变了。
        person4.setCarPrice(400000);
        System.out.println(person3.toString());
        System.out.println(person4.toString());

        List<Person> people1 = Arrays.asList(person1, person2, person3);
        List<Person> people2 = new ArrayList<>();
        people2.addAll(people1);
//        for (Person person : people1) {
//            System.out.println(person);
//        }
////        people1.clear();
//        for (Person person : people2) {
//            System.out.println(person);
//        }
    }

    @Data
    static class Car {
        String name;
        Integer price;

        public Car(String name, Integer price) {
            this.name = name;
            this.price = price;
        }
    }
}
