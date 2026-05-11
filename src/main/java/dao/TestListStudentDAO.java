package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDAO extends DAO {

    // ベースSQL
    private String baseSql =
        "select "
      + "s.name as subject_name, "
      + "s.cd as subject_cd, "
      + "t.no as num, "
      + "t.point as point "
      + "from test t "
      + "join subject s on t.subject_cd = s.cd ";

    /**
     * ResultSet → List<TestListStudent> 変換
     */
    private List<TestListStudent> postFilter(ResultSet rs)
            throws Exception {

        List<TestListStudent> list = new ArrayList<>();

        while (rs.next()) {

            TestListStudent tls = new TestListStudent();

            tls.setSubjectName(rs.getString("subject_name"));
            tls.setSubjectCd(rs.getString("subject_cd"));
            tls.setNum(rs.getInt("num"));
            tls.setPoint(rs.getInt("point"));

            list.add(tls);
        }

        return list;
    }

    /**
     * 学生ごとのテスト一覧取得
     */
    public List<TestListStudent> filter(Student student)
            throws Exception {

        List<TestListStudent> list = new ArrayList<>();

        String sql = baseSql
                + "where t.student_no = ?";

        try (
            var con = getConnection();
            var st = con.prepareStatement(sql);
        ) {

            st.setString(1, student.getNo());

            ResultSet rs = st.executeQuery();

            list = postFilter(rs);
        }

        return list;
    }
}