package a_过滤器模式.criteria;

import a_过滤器模式.demo.Person;

import java.util.List;

/**
 * @author guya on 2018/12/10
 */
public class AndCriteria implements Criteria {

    private Criteria criteria;
    private Criteria otherCriteria;

    public AndCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    /**
     * 第一种标准和第二种标准都满足的
     */
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> firstCriteriaPersons = criteria.meetCriteria(persons);// 第一种方式筛选过再使用另一种方法筛选
        return otherCriteria.meetCriteria(firstCriteriaPersons);
    }

}