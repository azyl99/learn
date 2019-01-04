package a_过滤器模式.criteria;

import a_过滤器模式.demo.Person;

import java.util.List;

/**
 * @author guya on 2018/12/10
 */
public class OrCriteria implements Criteria {

    private Criteria criteria;
    private Criteria otherCriteria;

    public OrCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    /**
     * 第一种标准或者第二种标准中满足一个的
     */
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> firstCriteriaItems = criteria.meetCriteria(persons);//第一种标准筛选
        List<Person> otherCriteriaItems = otherCriteria.meetCriteria(persons);//第二种标准筛选

        for (Person person : otherCriteriaItems) {//遍历第二种方式筛选的信息
            if (!firstCriteriaItems.contains(person)) {//如果第一种标准筛选过的人中不存在的，则加入到
                firstCriteriaItems.add(person);
            }
        }
        return firstCriteriaItems;
    }
}