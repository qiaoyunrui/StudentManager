package function.teacher.InputGrade

import data.MiniCourse
import data.MiniGrade
import db.DBService
import java.sql.ResultSet
import java.sql.Statement
import java.util.*

/**
 * Created by qiao1 on 2017/1/3.
 */
class InputGradePresenter {
    private val mDBMS by lazy { DBService.getInstance() }
    private var mStatement: Statement? = null

    init {
        try {
            mDBMS.connect()
            mStatement = mDBMS.statement
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private var courses: Vector<MiniCourse> = Vector<MiniCourse>()

    /**
     * 返回教授的所有课程
     */
    fun queryAllCourses(teacher_no: String): Vector<MiniCourse> {
        courses.clear()
        var sql = "SELECT course.Cno,course.Cname FROM course, " +
                "ct WHERE ct.Tno = '$teacher_no' AND ct.Cno = course.cno;"
        try {
            dealCourseResultSet(mStatement?.executeQuery(sql))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return courses
    }

    fun dealCourseResultSet(result_set: ResultSet?): Vector<MiniCourse> {
        if (result_set != null) {
            while (result_set.next()) {
                courses.add(MiniCourse(result_set.getString(1), result_set.getString(2)))
            }
        }
        return courses
    }

    private var grades: Vector<MiniGrade> = Vector<MiniGrade>()

    fun queryGrades(course_no: String): Vector<MiniGrade> {
        var sql = "SELECT grade.Sno,student.Sname,grade.Score FROM grade,student " +
                "WHERE grade.Sno = student.Sno AND grade.Cno = '$course_no';"
        grades.clear()
        try {
            dealGradeResultSet(mStatement?.executeQuery(sql))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return grades
    }

    fun dealGradeResultSet(result_set: ResultSet?): Vector<MiniGrade> {
        if (result_set != null) {
            while (result_set.next()) {
                grades.add(MiniGrade(result_set.getString(1),
                        result_set.getString(2),
                        result_set.getString(3)))
            }
        }
        return grades
    }

    /**
     * 更新成绩
     */
    fun updateGrade(grades: Vector<MiniGrade>, course_no: String): Int {

        var result_code = 0
        grades.forEach {
            if (mStatement?.executeUpdate("UPDATE grade SET Score = '${it.score}' WHERE Cno = '$course_no' AND  Sno = '${it.no}';")!! <= 0) {
                return -1
            }
        }
        return result_code
    }

    fun destroy() {
        mDBMS.close()
    }

}