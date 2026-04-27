package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDAO extends DAO {

    //ResultSet → List<ClassNum>
    private List<ClassNum> postFilter(ResultSet rs, School school) throws Exception {

        List<ClassNum> list = new ArrayList<>();

        while (rs.next()) {
            ClassNum c = new ClassNum();

            c.setClassNum(rs.getString("class_num"));
            c.setSchool(school);

            list.add(c);
        }

        return list;
    }

    //クラス番号一覧取得
    public List<ClassNum> filter(School school) throws Exception {

        Connection con = getConnection();

        String sql = "SELECT DISTINCT class_num FROM student WHERE school_cd = ? ORDER BY class_num";

        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, school.getCd());

        ResultSet rs = st.executeQuery();

        List<ClassNum> list = postFilter(rs, school);

        st.close();
        con.close();

        return list;
    }
}