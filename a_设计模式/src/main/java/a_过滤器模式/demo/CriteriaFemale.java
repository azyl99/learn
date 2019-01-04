package a_过滤器模式.demo;

import a_过滤器模式.criteria.Criteria;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guya on 2018/12/10
 */
public class CriteriaFemale implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> malePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if (person.getGender().equalsIgnoreCase("FEMALE")) {
                malePersons.add(person);
            }
        }
        return malePersons;
    }
}