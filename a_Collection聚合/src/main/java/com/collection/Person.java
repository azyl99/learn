package com.collection;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Person {

    int id;
    String name;
    int age;
    boolean gender;

    public static void test() {
        List<Person> people = new ArrayList<>();
        people.add(new Person(1,"zyl",20, false));
        people.add(new Person(2,"zyl",20, false));
        people.add(new Person(3,"gmy",21, false));
        people.add(new Person(4,"zzz",22, true));

        System.out.println(people.stream().map(Person::getName).collect(Collectors.toList()).toString());
        // List --> Set 去重复
        System.out.println(people.stream().collect(Collectors.toSet()).toString());
        System.out.println(people.stream().map(Person::getAge).reduce(0,(sum, age) -> sum + age) * 1.0 / people.size());

        // filter 筛选
        System.out.println(people.stream().filter(person -> person.isGender() == false).collect(Collectors.toSet()).toString());

        List nums = Arrays.asList(1, 3, null, 8, 7, 8, 13, 10);
        nums.stream().filter(num -> num != null).distinct().forEach((x) -> System.out.print(x + " "));
        nums.stream().filter(num -> num != null).distinct().forEach(System.out::print);

        System.out.println("\n-----------");
        Map<Integer, Person> map = people.stream().collect(Collectors.toMap(Person::getId, Function.identity()));// t->t
        System.out.println(map);
        System.out.println(map.get(1).getName());
        Map<Integer, String> m = people.stream().collect(Collectors.toMap(Person::getId, Person::getName));
        System.out.println(m);
    }

    public static void main(String[] args) {
        // test();

        String x = "2, 35, 52, 9, 30, 31";
//        List<String> list = Arrays.asList(x.split(","));
        Set<Long> set = Arrays.asList(x.split(",")).stream().map(t->Long.valueOf(t.trim())).collect(Collectors.toSet());
        set.forEach(t -> System.out.print(t + " "));
    }
}
