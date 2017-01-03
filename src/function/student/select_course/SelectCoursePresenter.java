package function.student.select_course;

import data.Course;
import db.DBService;

import java.sql.Statement;
import java.util.*;

/**
 * Created by tianlong on 2017/1/2.
 */
public class SelectCoursePresenter {

    private DBService mDBMS;
    private Statement mStatement;

    public SelectCoursePresenter() {
        try {
            mDBMS = DBService.getInstance();
            mStatement = mDBMS.getStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Statement getmStatement(){
        return mStatement;
    }

    public void closeDB() {
        try {
            mDBMS.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
