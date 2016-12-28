package db;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库服务
 * Created by qiao1 on 2016/12/28.
 */
public class DBService {

    private static final String MY_SQL_DRIVER_CLASS = "com.mysql.jdbc.Driver";

    private static final String JDBC = "jdbc";
    private static final String MYSQL = "mysql";
    private static final String DBMS_URL = "127.0.0.1";
    private static final String DBMS_PORT = "2333";
    private static final String DB_NAME = "student_manager";

    private static final String DBMS_USERNAME = "root";
    private static final String DBMS_PASSWD = "qiaoyunrui1995";

    private Connection mConn = null;
    private Statement mState = null;

    private final static class DBServiceHolder {
        private static final DBService sInstance = new DBService();
    }

    public static DBService getInstance() {
        return DBServiceHolder.sInstance;
    }

    /**
     * 连接数据库
     */
    public void connect() throws Exception {
        try {
            Class.forName(MY_SQL_DRIVER_CLASS);
            mConn = DriverManager
                    .getConnection(getURL(), DBMS_USERNAME, DBMS_PASSWD);
            mState = mConn.createStatement();
        } catch (ClassNotFoundException e) {
            throw new Exception("数据库驱动异常（可能是没有导入数据库驱动）： " + e.getMessage());
        } catch (SQLException e) {
            throw new Exception("数据库连接异常： " + e.getSQLState());
        }
    }

    public Statement getStatement() {
        return mState;
    }

    /**
     * 断开连接，并关闭数据库
     *
     * @throws Exception
     */
    public void close() throws Exception {
        try {
            if (mConn != null) {
                mConn.close();
                mConn = null;
            }
            if (mState != null) {
                mState.close();
                mState = null;
            }
        } catch (SQLException e) {
            throw new Exception("关闭数据库出现异常");
        }
    }

    /**
     * 获取完整的URL
     *
     * @return
     */
    public String getURL() {
        return JDBC + ":" + MYSQL + "://" + DBMS_URL + ":" + DBMS_PORT +
                "/" + DB_NAME;
    }

}
