package function.student.query_grade

import data.Grade
import data.MiniGrade
import db.DBService
import java.sql.ResultSet
import java.sql.Statement
import java.util.*

/**
 * Created by qiao1 on 2017/1/3.
 */
class QueryGradePresenter {

    private val mDBMS: DBService by lazy { DBService.getInstance() }
    private var mStatement: Statement? = null

    init {
        try {
            mDBMS.connect()
            mStatement = mDBMS.statement
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private var grades = Vector<MiniGrade>()

    /**
     * 查询该该学生的全部成绩
     */
    fun queryAllGrade(student_no: String): Vector<MiniGrade> {
        var sql = "SELECT course.Cno, Cname, Score FROM grade, course " +
                "WHERE Sno = '$student_no' AND course.Cno = grade.Cno;"
        try {
            dealResultSet(mStatement?.executeQuery(sql))
        } catch (e: Exception) {

        }
        return grades
    }

    fun dealResultSet(result_set: ResultSet?): Vector<MiniGrade> {
        if (result_set != null) {
            while (result_set.next()) {
                var score = ""
                if (result_set.getString(3) == null) {
                    score = "无"
                } else {
                    score = result_set.getString(3)
                }
                grades.add(MiniGrade(result_set.getString(1),
                        result_set.getString(2),
                        score))
            }
        }
        return grades
    }

    fun destroy() {
        mDBMS.close()
    }

}