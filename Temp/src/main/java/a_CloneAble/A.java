package a_CloneAble;

import lombok.Data;

@Data
public class A implements Cloneable {

    // String的特殊性在于：
    //    因为他为引用型，而且他指向的值为常量，克隆出来的对象改变他的值。实际上是改变了克隆出来对象String类型成员的指向，不会影响被克隆对象的值及其指向。
    // 这告诉我们支持更方便实现克隆的一种途径：将自己定义的类编写为不可更改。
    String name;
    Integer age;
    Car car;

    public A(String name, Integer age, Car car) {
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
        A obj = new A(name, age, car);
        return (Object)obj;
    }

    public static void main(String[] args) {
        A a1 = new A("zyl", 21, new Car("Fort", 100000));
        A a2 = (A) a1.clone();
        a2.setName("gmy");  // String是不可修改的实例，这一步改变了a2.name的句柄
        a2.setAge(22);
        a2.setCar(new Car("QQ", 200000));// a2.car的句柄改为指向new出来的这个实例。
        System.out.println(a1.toString());
        System.out.println(a2.toString());

        A a3 = new A("zyl", 21, new Car("Fort", 300000));
        A a4 = (A) a3.clone();
        a4.setName("gmy");
        a4.setAge(22);
        a4.setCarName("DAZ");// a4.car和a3.car指向同一个Car实例，但内容却被改变了。
        a4.setCarPrice(400000);
        System.out.println(a3.toString());
        System.out.println(a4.toString());
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
