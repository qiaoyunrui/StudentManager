package function.student.main.selectedcourse;

import data.CourseX;
import data.user.Student;
import db.DBService;
import user_service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * Created by tianlong on 2017/1/4.
 */
public class QuerySelectcoursePresenter {
    private DBService mDBMS = null;
    private Statement mStatement = null;
    private Vector<CourseX> res = new Vector<>();

    public QuerySelectcoursePresenter() {
        mDBMS = DBService.getInstance();
        try {
            mDBMS.connect();
            mStatement = mDBMS.getStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Vector search(String key) {

        String sql = "SELECT DISTINCT c.Cno," +
                "c.Cname," +
                "c.Cdesc," +
                "c.Ccapacity," +
                "c.Cselected," +
                "c.Cterm," +
                "t.Tname " +
                "FROM course c," +
                "teacher t," +
                "ct a " +
                "where c.Cterm = '" + ((Student) UserService.getInstance().getCurrentUser()).getTerm() + "' " +
                "AND c.Cno = a.Cno " +
                "AND a.Tno = t.Tno " +
                "AND (c.Cname like '%" + key + "%' " +
                "OR t.Tname like '%" + key + "%');";
        try {
            res = dealResultSet(mStatement.executeQuery(sql));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public Vector dealResultSet(ResultSet rs) {
        res.clear();
        if (rs != null) {
            try {
                while (rs.next()) {
                    res.add(new CourseX(rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getString(7)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public int selectcourse(String sno, String cno) {
        int code = -1;
        try {
            ResultSet rs = mStatement
                    .executeQuery("select Cno from grade where Sno = '" + sno + "' and Cno = '" + cno + "';");
            if (!rs.next()) {
                if (mStatement.executeUpdate("update course set Cselected = Cselected + 1 where Cno = '" + cno + "'and Cselected < Ccapacity") > 0) {
                    code = 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }

    public void closeDB() {
        try {
            mDBMS.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
