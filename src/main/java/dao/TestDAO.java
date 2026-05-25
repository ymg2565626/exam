package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDAO extends DAO {

	private String baseSql = "SELECT * FROM test ";

	// 単一取得
	public Test get(Student student, Subject subject, School school, int no)
			throws Exception {

		Test test = null;

		Connection con = getConnection();

		PreparedStatement st = null;

		ResultSet rs = null;

		try {

			String sql =
				"SELECT * FROM test "
				+ "WHERE student_no = ? "
				+ "AND subject_cd = ? "
				+ "AND school_cd = ? "
				+ "AND no = ?";

			st = con.prepareStatement(sql);

			st.setString(1, student.getNo());
			st.setString(2, subject.getCd());
			st.setString(3, school.getCd());
			st.setInt(4, no);

			rs = st.executeQuery();

			StudentDAO studentDao = new StudentDAO();
			SubjectDAO subjectDao = new SubjectDAO();

			if (rs.next()) {

				test = new Test();

				test.setStudent(
					studentDao.get(
						rs.getString("student_no")));

				test.setClassNum(
					rs.getString("class_num"));

				test.setSubject(
					subjectDao.get(
						rs.getString("subject_cd"),
						school));

				test.setSchool(school);

				test.setNo(
					rs.getInt("no"));

				test.setPoint(
					rs.getInt("point"));
			}

		} finally {

			if (rs != null) {
				rs.close();
			}

			if (st != null) {
				st.close();
			}

			if (con != null) {
				con.close();
			}
		}

		return test;
	}

	// ResultSet -> List変換
	private List<Test> postFilter(
			ResultSet rs,
			School school) throws Exception {

		List<Test> list = new ArrayList<>();

		StudentDAO studentDao = new StudentDAO();
		SubjectDAO subjectDao = new SubjectDAO();

		while (rs.next()) {

			Test test = new Test();

			test.setStudent(
				studentDao.get(
					rs.getString("student_no")));

			test.setClassNum(
				rs.getString("class_num"));

			test.setSubject(
				subjectDao.get(
					rs.getString("subject_cd"),
					school));

			test.setSchool(school);

			test.setNo(
				rs.getInt("no"));

			test.setPoint(
				rs.getInt("point"));

			list.add(test);
		}

		return list;
	}

	// 一覧取得
	public List<Test> filter(
			int entYear,
			String classNum,
			Subject subject,
			int no,
			School school) throws Exception {

		List<Test> list = new ArrayList<>();

		Connection con = getConnection();

		PreparedStatement st = null;

		ResultSet rs = null;

		try {

			String sql =
				"SELECT test.* FROM test "
				+ "JOIN student ON test.student_no = student.no "
				+ "AND test.school_cd = student.school_cd "
				+ "WHERE student.ent_year = ? "
				+ "AND test.class_num = ? "
				+ "AND test.subject_cd = ? "
				+ "AND test.no = ? "
				+ "AND test.school_cd = ? "
				+ "ORDER BY test.student_no";

			st = con.prepareStatement(sql);

			st.setInt(1, entYear);
			st.setString(2, classNum);
			st.setString(3, subject.getCd());
			st.setInt(4, no);
			st.setString(5, school.getCd());

			rs = st.executeQuery();

			list = postFilter(rs, school);

		} finally {

			if (rs != null) {
				rs.close();
			}

			if (st != null) {
				st.close();
			}

			if (con != null) {
				con.close();
			}
		}

		return list;
	}

	// 保存
	public boolean save(List<Test> list) throws Exception {

		Connection con = getConnection();

		boolean result = true;

		try {

			for (Test test : list) {

				boolean saveResult =
						save(test, con);

				if (!saveResult) {
					result = false;
				}
			}

		} finally {

			if (con != null) {
				con.close();
			}
		}

		return result;
	}

	// 単体保存
	private boolean save(
			Test test,
			Connection con) throws Exception {

		PreparedStatement st = null;

		int count = 0;

		try {

			Test old =
				get(
					test.getStudent(),
					test.getSubject(),
					test.getSchool(),
					test.getNo());

			if (old == null) {

				String sql =
						"INSERT INTO test("
						+ "student_no, "
						+ "subject_cd, "
						+ "school_cd, "
						+ "class_num, "
						+ "no, "
						+ "point"
						+ ") VALUES(?, ?, ?, ?, ?, ?)";

				st = con.prepareStatement(sql);

			} else {

				String sql =
					"UPDATE test SET "
					+ "point = ? "
					+ "WHERE student_no = ? "
					+ "AND subject_cd = ? "
					+ "AND school_cd = ? "
					+ "AND no = ?";

				st = con.prepareStatement(sql);

				st.setInt(1, test.getPoint());
				st.setString(2, test.getStudent().getNo());
				st.setString(3, test.getSubject().getCd());
				st.setString(4, test.getSchool().getCd());
				st.setInt(5, test.getNo());

				count = st.executeUpdate();

				return count > 0;
			}

			st.setString(1, test.getStudent().getNo());
			st.setString(2, test.getSubject().getCd());
			st.setString(3, test.getSchool().getCd());
			st.setString(4, test.getClassNum());
			st.setInt(5, test.getNo());
			st.setInt(6, test.getPoint());

			count = st.executeUpdate();

		} finally {

			if (st != null) {
				st.close();
			}
		}

		return count > 0;
	}

	public List<Test> filter(Student student)
			throws Exception {

		List<Test> list =
				new ArrayList<>();

		Connection con =
				getConnection();

		PreparedStatement st =
				null;

		ResultSet rs =
				null;

		try {

			String sql =
				"SELECT * FROM test "
				+ "WHERE student_no = ? "
				+ "ORDER BY no";

			st = con.prepareStatement(sql);

			st.setString(
					1,
					student.getNo());

			rs = st.executeQuery();

			list = postFilter(
					rs,
					student.getSchool());

		} finally {

			if (rs != null) {
				rs.close();
			}

			if (st != null) {
				st.close();
			}

			if (con != null) {
				con.close();
			}
		}

		return list;
	}

}
