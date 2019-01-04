package a_过滤器模式.demo;

/**
 * @author guya on 2018/12/10
 */
public class Person {
    private String name;
    private String gender;
    private String maritalStatus;//婚姻状态

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getMaritalStatus() {
        return maritalStatus;
    }
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    public Person(String name, String gender, String maritalStatus) {
        super();
        this.name = name;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
    }
    public Person() {
        super();
    }

}
