package A_同类copy;

import lombok.Data;

import java.io.Serializable;

/**
 * @author guya
 * @date 2018/8/15
 */
@Data
public class A1 implements Serializable {

    String name;
    Integer age;

    public String toString() {
        return name + ":" + age;
    }
}
