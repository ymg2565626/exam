package bean;

import java.io.Serializable;

public class Test implements Serializable {

	private Student student;
	private String classNum;
	private Subject subject;
	private School school;

	// 回数
	private int count;

	// 点数
	private int point;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public void setNo(int no) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public int getNo() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

}
