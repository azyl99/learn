package A_同类copy;

import lombok.Data;

/**
 * @author guya
 * @date 2018/8/15
 */
@Data
public class A2 {
    String name;
    Integer age;

    public String toString() {
        return name + ":" + age;
    }
}
