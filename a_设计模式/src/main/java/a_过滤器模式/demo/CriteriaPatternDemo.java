package a_过滤器模式.demo;

import a_过滤器模式.criteria.AndCriteria;
import a_过滤器模式.criteria.Criteria;
import a_过滤器模式.criteria.OrCriteria;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guya on 2018/12/10
 */
public class CriteriaPatternDemo {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<Person>();

        //一群人
        persons.add(new Person("Robert", "Male", "Single"));
        persons.add(new Person("John", "Male", "Married"));
        persons.add(new Person("Laura", "Female", "Married"));
        persons.add(new Person("Diana", "Female", "Single"));
        persons.add(new Person("Mike", "Male", "Single"));
        persons.add(new Person("Bobby", "Male", "Single"));


        Criteria male = new CriteriaMale();//筛选男人
        Criteria female = new CriteriaFemale();//筛选女人
        Criteria single = new CriteriaSingle();//筛选单身的
        Criteria singleMale = new AndCriteria(single, male);//筛选单身的男人
        Criteria singleOrFemale = new OrCriteria(single, female);//筛选所有的单身的，所有的女人

        System.out.println("Males: ");
        printPersons(male.meetCriteria(persons));

        System.out.println("\nFemales: ");
        printPersons(female.meetCriteria(persons));

        System.out.println("\nSingle Males: ");
        printPersons(singleMale.meetCriteria(persons));

        System.out.println("\nSingle Or Females: ");
        printPersons(singleOrFemale.meetCriteria(persons));
    }

    public static void printPersons(List<Person> persons) {
        for (Person person : persons) {
            System.out.println("Person : [ Name : " + person.getName()
                    + ", Gender : " + person.getGender()
                    + ", Marital Status : " + person.getMaritalStatus() + " ]");
        }
    }
}