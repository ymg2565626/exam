package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;

public class SchoolDAO extends DAO {

    public School get(String cd) throws Exception {

        School school = null;

        Connection con = getConnection();

        String sql = "SELECT * FROM SCHOOL WHERE CD = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, cd);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            school = new School();
            school.setCd(rs.getString("CD"));
            school.setName(rs.getString("NAME"));
        }

        rs.close();
        ps.close();
        con.close();

        return school;
    }
}
