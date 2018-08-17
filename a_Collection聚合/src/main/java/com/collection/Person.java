package com.collection;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Person {

    String name;
    int age;
    boolean gender;

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("zyl",20, false));
        people.add(new Person("zyl",20, false));
        people.add(new Person("gmy",21, false));
        people.add(new Person("zzz",22, true));

        System.out.println(people.stream().map(Person::getName).collect(Collectors.toList()).toString());
        // List --> Set 去重复
        System.out.println(people.stream().collect(Collectors.toSet()).toString());
        System.out.println(people.stream().map(Person::getAge).reduce(0,(sum, age) -> sum + age) * 1.0 / people.size());

        // filter 筛选
        System.out.println(people.stream().filter(person -> person.isGender() == false).collect(Collectors.toSet()).toString());

        List nums = Arrays.asList(1, 3, null, 8, 7, 8, 13, 10);
        nums.stream().filter(num -> num != null).distinct().forEach((x) -> System.out.print(x + " "));
        nums.stream().filter(num -> num != null).distinct().forEach(System.out::print);
    }
}
