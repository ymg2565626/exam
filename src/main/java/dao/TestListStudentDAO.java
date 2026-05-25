package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.TestListStudent;

public class TestListStudentDAO extends DAO {

    // ベースSQL
	private String baseSql =
		    "select "
		  + "st.no as student_no, "
		  + "st.name as student_name, "
		  + "st.class_num as class_num, "
		  + "s.name as subject_name, "
		  + "s.cd as subject_cd, "
		  + "t.no as num, "
		  + "t.point as point "
		  + "from test t "
		  + "join subject s "
		  + "on t.subject_cd = s.cd "
		  + "join student st "
		  + "on t.student_no = st.no ";

    /**
     * ResultSet → List<TestListStudent> 変換
     */
    private List<TestListStudent> postFilter(ResultSet rs)
            throws Exception {

        List<TestListStudent> list = new ArrayList<>();

        while (rs.next()) {

            TestListStudent tls = new TestListStudent();
            
            tls.setStudentNo(rs.getString("student_no"));
            tls.setStudentName(rs.getString("student_name"));
            tls.setClassNum(rs.getString("class_num"));

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
    public List<TestListStudent> filter(
            String entYear,
            String classNum,
            String subjectCd,
            String studentNo
    ) throws Exception {

        List<TestListStudent> list =
            new ArrayList<>();

        String sql = baseSql
                + "where 1=1"
                + "where st.no = ?";

        // 入学年度
        if (entYear != null && !entYear.isEmpty()) {
            sql += "and st.ent_year = ? ";
        }

        // クラス
        if (classNum != null && !classNum.isEmpty()) {
            sql += "and st.class_num = ? ";
        }

        // 科目
        if (subjectCd != null && !subjectCd.isEmpty()) {
            sql += "and t.subject_cd = ? ";
        }

        try (
            var con = getConnection();
            var st = con.prepareStatement(sql);
        ) {

            int idx = 1;

            // 入学年度
            if (entYear != null && !entYear.isEmpty()) {
                st.setString(idx++, entYear);
            }

            // クラス
            if (classNum != null && !classNum.isEmpty()) {
                st.setString(idx++, classNum);
            }

            // 科目
            if (subjectCd != null && !subjectCd.isEmpty()) {
                st.setString(idx++, subjectCd);
            }
            
            st.setString(1, studentNo);

            ResultSet rs = st.executeQuery();

            list = postFilter(rs);
        }

        return list;
    }
}
