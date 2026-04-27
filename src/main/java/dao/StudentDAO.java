package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDAO extends DAO {

    // ResultSet → List<Student>
    private List<Student> postFilter(ResultSet rs) throws Exception {

        List<Student> list = new ArrayList<>();

        while (rs.next()) {
            Student s = new Student();

            s.setNo(rs.getInt("no"));
            s.setName(rs.getString("name"));
            s.setEntYear(rs.getInt("ent_year"));
            s.setClassNum(rs.getString("class_num"));
            s.setAttend(rs.getBoolean("is_attend"));

            School school = new School();
            school.setCd(rs.getString("school_cd"));
            s.setSchool(school);

            list.add(s);
        }

        return list;
    }

    // 一覧（在学）
    public List<Student> filter(School school, boolean isAttend) throws Exception {

        Connection con = getConnection();

        String sql = "SELECT * FROM student WHERE school_cd = ? AND is_attend = ? ORDER BY no";

        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, school.getCd().trim());
        st.setBoolean(2, isAttend);

        ResultSet rs = st.executeQuery();

        List<Student> list = postFilter(rs);

        st.close();
        con.close();

        return list;
    }

    // 条件検索
    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {

        Connection con = getConnection();

        String sql = "SELECT * FROM student WHERE school_cd = ? AND ent_year = ? AND class_num = ? AND is_attend = ? ORDER BY no";

        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, school.getCd());
        st.setInt(2, entYear);
        st.setString(3, classNum);
        st.setBoolean(4, isAttend);

        ResultSet rs = st.executeQuery();

        List<Student> list = postFilter(rs);

        st.close();
        con.close();

        return list;
    }

    // 1件取得
    public Student get(int no) throws Exception {

        Connection con = getConnection();

        String sql = "SELECT * FROM student WHERE no = ?";

        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, no);

        ResultSet rs = st.executeQuery();

        List<Student> list = postFilter(rs);

        st.close();
        con.close();

        if (list.size() > 0) {
            return list.get(0);
        }

        return null;
    }

    // 登録
    public boolean save(Student s) throws Exception {

        Connection con = getConnection();

        String sql = "INSERT INTO student (no, name, ent_year, class_num, is_attend, school_cd) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement st = con.prepareStatement(sql);

        st.setInt(1, s.getNo());
        st.setString(2, s.getName());
        st.setInt(3, s.getEntYear());
        st.setString(4, s.getClassNum());
        st.setBoolean(5, s.isAttend());
        st.setString(6, s.getSchool().getCd());

        int line = st.executeUpdate();

        st.close();
        con.close();

        return line > 0;
    }

    // ★更新（今回追加）
    public boolean update(Student s) throws Exception {

        Connection con = getConnection();

        String sql = "UPDATE student SET name=?, ent_year=?, class_num=?, is_attend=? WHERE no=? AND school_cd=?";

        PreparedStatement st = con.prepareStatement(sql);

        st.setString(1, s.getName());
        st.setInt(2, s.getEntYear());
        st.setString(3, s.getClassNum());
        st.setBoolean(4, s.isAttend());
        st.setInt(5, s.getNo());
        st.setString(6, s.getSchool().getCd());

        int line = st.executeUpdate();

        st.close();
        con.close();

        return line > 0;
    }
}