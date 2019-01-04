package a_过滤器模式.demo;

import a_过滤器模式.criteria.Criteria;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guya on 2018/12/10
 */
public class CriteriaSingle implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> singlePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if(person.getMaritalStatus().equalsIgnoreCase("SINGLE")){
                singlePersons.add(person);
            }
        }
        return singlePersons;
    }
}