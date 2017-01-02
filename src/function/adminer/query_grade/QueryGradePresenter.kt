package function.adminer.query_grade

import data.Grade
import db.DBService
import java.sql.ResultSet
import java.sql.Statement
import java.util.*

/**
 * Created by qiao1 on 2017/1/1.
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

    private var result: Vector<Grade> = Vector<Grade>()

    /**
     * 从数据库查询信息
     */
    fun search(key: String): Vector<Grade> {
        var sql = "SELECT student.Sno," +
                "student.Sname," +
                "course.Cno," +
                "course.Cname," +
                "course.Cterm," +
                "Course.Cdesc," +
                "teacher.Tname," +
                "grade.Score" +
                " FROM course, student, teacher, ct, grade " +
                "WHERE " +
                "grade.Cno = course.Cno AND " +
                "grade.Sno = student.Sno AND " +
                "course.Cno = ct.Cno AND " +
                "teacher.Tno = ct.Tno AND " +
                "(teacher.Tname LIKE '%$key%' OR " +
                "student.Sname LIKE '%$key%'  OR " +
                "course.Cterm LIKE '%$key%' OR " +
                "course.Cname LIKE '%$key%');"
        try {
            result = mergeRepeat(dealResultSet(mStatement?.executeQuery(sql)))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    /**
     * 将ResultSet转化为Vector
     */
    fun dealResultSet(result_set: ResultSet?): Vector<Grade> {
        result.clear()
        if (result_set == null) {
            return result
        }
        while (result_set.next()) {
            result.add(Grade(
                    result_set.getString(1),
                    result_set.getString(2),
                    result_set.getString(3),
                    result_set.getString(4),
                    result_set.getString(5),
                    result_set.getString(6),
                    result_set.getString(7),
                    result_set.getString(8)))
        }
        return result
    }

    /**
     * 合并重复项
     */
    fun mergeRepeat(from: Vector<Grade>): Vector<Grade> {
        var to = Vector<Grade>()
        var flag = false    //标志该项是否为重复项
        from.forEach { i ->
            flag = false
            to.forEach { j ->
                if (i.courseNo == j.courseNo && i.courseTeachers != j.courseTeachers) { //重复项
                    j.courseTeachers += "、${i.courseTeachers}"
                    flag = true
                }
            }
            if (!flag) {
                to.add(i)
            }
        }
        to.forEach(::println)
        return to
    }

    fun closeDB() {
        mDBMS.close()
    }

}