
package bean;

import java.io.Serializable;

public class Subject implements Serializable {

    private String cd;
    private String name;
    private School school;

    public String getId() {
        return cd;
    }

    public void setId(String id) {
        this.cd = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
