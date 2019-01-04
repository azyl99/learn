package a_过滤器模式.criteria;

/**
 * @author guya on 2018/12/10
 */
import a_过滤器模式.demo.Person;

import java.util.List;

public interface Criteria {
    /** 筛选出符合要求的 */
    List<Person> meetCriteria(List<Person> persons);
}