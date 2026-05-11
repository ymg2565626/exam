package bean;

public class TestListStudent {

    // 学生情報
    private String studentNo;
    private String studentName;
    private String classNum;

    // 科目情報
    private String subjectName;
    private String subjectCd;

    // テスト情報
    private int num;
    private int point;

    // getter,setter

    // studentNo
    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    // studentName
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    // classNum
    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    // subjectName
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    // subjectCd
    public String getSubjectCd() {
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    // num
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    // point
    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
