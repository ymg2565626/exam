package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDAO extends DAO {

    // 科目コード + 学校で1件取得
    public Subject get(String cd, School school) throws Exception {

        Subject subject = null;

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM subject WHERE cd = ? AND school_cd = ?"
        );

        st.setString(1, cd);
        st.setString(2, school.getCd());

        ResultSet rs = st.executeQuery();

        if (rs.next()) {

            subject = new Subject();

            subject.setCd(rs.getString("cd"));
            subject.setName(rs.getString("name"));
            subject.setSchool(school);
        }

        rs.close();
        st.close();
        con.close();

        return subject;
    }

    // 学校ごとの科目一覧取得
    public List<Subject> filter(School school) throws Exception {

        List<Subject> list = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM subject WHERE school_cd = ?"
        );

        st.setString(1, school.getCd());

        ResultSet rs = st.executeQuery();

        while (rs.next()) {

            Subject subject = new Subject();

            subject.setCd(rs.getString("cd"));
            subject.setName(rs.getString("name"));
            subject.setSchool(school);

            list.add(subject);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }

    // 登録・更新
    public boolean save(Subject subject) throws Exception {

        Connection con = getConnection();

        // 既存データ確認
        Subject old = get(subject.getCd(), subject.getSchool());

        PreparedStatement st;

        // 新規登録
        if (old == null) {

            st = con.prepareStatement(
                "INSERT INTO subject(cd, name, school_cd) VALUES(?, ?, ?)"
            );

            st.setString(1, subject.getCd());
            st.setString(2, subject.getName());
            st.setString(3, subject.getSchool().getCd());

        // 更新
        } else {

            st = con.prepareStatement(
                "UPDATE subject SET name=? WHERE cd=? AND school_cd=?"
            );

            st.setString(1, subject.getName());
            st.setString(2, subject.getCd());
            st.setString(3, subject.getSchool().getCd());
        }

        int count = st.executeUpdate();

        st.close();
        con.close();

        return count > 0;
    }

    // 削除
    public boolean delete(Subject subject) throws Exception {

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "DELETE FROM subject WHERE cd=? AND school_cd=?"
        );

        st.setString(1, subject.getCd());
        st.setString(2, subject.getSchool().getCd());

        int count = st.executeUpdate();

        st.close();
        con.close();

        return count > 0;
    }
}
