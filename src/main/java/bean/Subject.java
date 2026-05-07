package bean;

import java.io.Serializable;

public class Subject implements Serializable {

    private String cd;
    private String name;
    private School school;

    // DAO側の getCd() に対応
    public String getCd() {
        return cd;
    }

    // DAO側の setCd() に対応
    public void setCd(String cd) {
        this.cd = cd;
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
