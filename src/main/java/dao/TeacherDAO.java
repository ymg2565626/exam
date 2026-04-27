package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;

public class TeacherDAO extends DAO {

    public Teacher get(String id) throws Exception {

        Teacher teacher = null;

        Connection con = getConnection();

        String sql = "SELECT * FROM TEACHER WHERE ID = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            teacher = new Teacher();
            teacher.setId(rs.getString("ID"));
            teacher.setPassword(rs.getString("PASSWORD"));
            teacher.setName(rs.getString("NAME"));

            School school = new School();
            school.setCd(rs.getString("SCHOOL_CD").trim());
            teacher.setSchool(school);
        }

        rs.close();
        ps.close();
        con.close();

        return teacher;
    }

    // ログイン用
    public Teacher login(String id, String password) throws Exception {

        Teacher teacher = get(id);

        if (teacher != null && teacher.getPassword().equals(password)) {
            return teacher;
        }

        return null;
    }
}