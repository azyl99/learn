package unsolved;

import java.util.ArrayList;
import java.util.Comparator;

public class Solution1 {

    static class Person {
        int index;
        int diff;
    }

    public int twoCitySchedCost(int[][] costs) {
        ArrayList<Person> list = new ArrayList<>(costs.length);
        for (int i = 0; i < costs.length; i++) {
            list.add(i, new Person());
            list.get(i).index = i;
            list.get(i).diff = costs[i][0] - costs[i][1];
        }
        int sum = 0;
        list.sort(Comparator.comparingInt(o -> o.diff));
        for (int i = 0; i < list.size() / 2; i++) {
            sum += costs[list.get(i).index][0];
        }
        for (int i = list.size() / 2; i < list.size(); i++) {
            sum += costs[list.get(i).index][1];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] costs = {{10,20},{30,200},{400,50},{30,20}};
        System.out.println(new Solution1().twoCitySchedCost(costs));
    }
}
