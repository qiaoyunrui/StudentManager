package function.student.person;


import data.user.Student;

import javax.swing.*;
import java.sql.*;


public class SelectStu {

    private Statement stmt;

    public SelectStu() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:2333/student_manager"
                    , "root", "qiaoyunrui1995");
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Student getStudentInfo(String no) {
        Student student = null;
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("select * from student WHERE Sno = '" + no + "'");

            if (rs.next()) {
                student = new Student(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public int changePasswd(String no, String old_passwd, String new_passwd) {
        int result_code = -1;
        String sql_confirm_passwd = "SELECT Spasswd FROM student WHERE Sno = '" + no + "';";
        String sql_update_passwd = "UPDATE student SET Spasswd = '" + new_passwd + "' WHERE Sno = '"
                + no + "';";
        try {
            ResultSet resultSet = stmt.executeQuery(sql_confirm_passwd);
            if (resultSet.next()) {
                System.out.println(resultSet.getString(1));
                if (resultSet.getString(1).equals(old_passwd)) {
                    System.out.println("C");
                    if (stmt.executeUpdate(sql_update_passwd) > 0) {
                        result_code = 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result_code;
    }

}