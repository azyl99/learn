package 类的加载.a1;

import lombok.Data;

@Data
public class Person {
    String name;
    int age;

    public void say() {
        System.out.println(name + age);
    }
}
