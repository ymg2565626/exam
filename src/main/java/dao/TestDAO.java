package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDAO extends DAO {

	private String baseSql = "SELECT * FROM test ";

	public Test get(Student student, Subject subject, School school, int no)
			throws Exception {
		// テストインスタンスを初期化
		Test test = new Test();
		// データベースへのコネクションを確立
		Connection con = getConnection();
		// プリペアードステートメント
		PreparedStatement st = null;

		try {
			// プリペアードステートメントにSQL文をセット
			st = con.prepareStatement("SELECT * FROM test WHERE student_cd = ? AND subject_cd = ? AND school_cd = ? AND no = ?");
			st.setString(1, student.getNo());
			st.setString(2, subject.getCd());
			st.setString(3, school.getCd());
			st.setInt(4, no);

			//リザルトセット
			ResultSet rs = st.executeQuery();

			//DAOの初期化
			StudentDAO studentDao = new StudentDAO();
			SubjectDAO subjectDao = new SubjectDAO();
			SchoolDAO schoolDao = new SchoolDAO();

			if (rs.next()) {
				test.setStudent(studentDao.get(rs.getString("student_cd")));
				test.setClassNum(rs.getString("class_num"));
				test.setSubject(subjectDao.get(rs.getString("subject_cd"), school));
				test.setSchool(schoolDao.get(rs.getString("school_cd")));
				test.setNo(rs.getInt("no"));
				test.setPoint(rs.getInt("point"));
			} else {
				// リザルトセットが存在しない場合
				// テストインスタンスにnullをセット
				test = null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (st != null) {
				try {
					st.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (con != null) {
				try {
					con.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return test;
	}

	private List<Test> postFilter(ResultSet resultSet, School school) throws Exception {

		// リストを初期化
		List<Test> list = new ArrayList<>();
		StudentDAO studentDao = new StudentDAO();
		SubjectDAO subjectDao = new SubjectDAO();
		try {
			// リザルトセットを全権走査
			while (resultSet.next()) {
				// テストインスタンスを初期化
				Test test = new Test();

				// テストインスタンスに検索結果をセット
				test.setStudent(studentDao.get(resultSet.getString("student_cd")));
				test.setClassNum(resultSet.getString("class_num"));
				test.setSubject(subjectDao.get(resultSet.getString("subject_cd"), school));
				test.setSchool(school);
				test.setNo(resultSet.getInt("no"));
				test.setPoint(resultSet.getInt("point"));
				// リストに追加
				list.add(test);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		return list;
	}

	//  // 成績一覧取得
	public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
		List<Test> list = new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = baseSql + "WHERE ent_year = ? " + "AND class_num = ? " + "AND subject_cd = ? "
					+ "AND no = ? " + "AND school_cd = ? " + "ORDER BY student_no";
			st = con.prepareStatement(sql);
			st.setInt(1, entYear);
			st.setString(2, classNum);
			st.setString(3, subject.getCd());
			st.setInt(4, num);
			st.setString(5, school.getCd());
			rs = st.executeQuery();
			list=postFilter(rs, school);

		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (st != null) {
				try {
					st.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (con != null) {
				try {
					con.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return list;
	}

	//登録
	public boolean save(List<Test> list) throws Exception {

		try {
			Connection con = getConnection();
			for (Test test : list) {
				 save(test, con);
			}
		}catch (Exception e) {
			throw e;
		}


		return true;
	}

	private boolean save(Test test, Connection connection) throws Exception {



		PreparedStatement st = null;
		int count =0;
		
		
		try {
			Test old=get(test.getStudent(),test.getSubject(),test.getSchool(),test.getNo());
		if(old ==null) {
			st = connection.prepareStatement("insert into test(student_no, subject_cd, school_cd ,No ,point ) values(?, ?, ?, ?, ? )");
			
			st.setString(1, test.getStudent().getNo());
			st.setString(2, test.getSubject().getCd());
			st.setString(3, test.getSchool().getCd());
			st.setInt(4, test.getNo());
			st.setInt(5, test.getPoint());
			
		} else {
			// 学生が存在した場合
			// プリペアードステートメントにUPDATE文をセット
			st = connection.prepareStatement("update test set student_no =?, subject_cd = ?, school_cd = ?, No= ?,point = ? where student_no=?");
			
			// プリペアードステートメントに値をバインド
			st.setString(1, test.getStudent().getNo());
			st.setString(2, test.getSubject().getCd());
			st.setString(3, test.getSchool().getCd());
			st.setInt(4, test.getNo());
			st.setInt(5, test.getPoint());

		}

		// プリペアードステートメントを実行
		count = st.executeUpdate();


		} catch (Exception e) {
		throw e;
	} finally {
		// プリペアードステートメントを閉じる
		if (st != null) {
			try {
				st.close();
			} catch (SQLException sqle) {
				throw sqle;
			}
		}
		// コネクションを閉じる
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException sqle) {
				throw sqle;
			}
		}
	}

	if (count > 0) {
		// 実行件数が1件以上ある場合
		return true;
	} else {
		// 実行件数が0件の場合
		return false;
	}
}
}

