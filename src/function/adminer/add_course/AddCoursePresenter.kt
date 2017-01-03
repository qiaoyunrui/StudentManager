package function.adminer.add_course

import data.Course
import data.user.Teacher
import db.DBService
import java.sql.Statement
import java.util.*

/**
 * Created by qiao1 on 2017/1/2.
 */
class AddCoursePresenter {

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

    fun closeDB() = mDBMS.close()

    /**~
     * 添加课程
     */
    fun addCourse(pair: Pair<Course, Vector<Teacher>>): Int {
        var result_code = -1
        var param = ""
        pair.second.forEach {
            param += "('${pair.first.no}','${it.no}'),"
        }
        param = param.subSequence(0, param.length - 1) as String
        var sql1 = "INSERT INTO `course` VALUES ('${pair.first.no}'," +
                "'${pair.first.name}'," +
                "'${pair.first.desc}'," +
                "${pair.first.capacity}," +
                "0," +
                "'${pair.first.term}');"
        var sql2 = "INSERT INTO `ct` VALUES $param;"
        try {
            if (mStatement?.executeUpdate(sql1)!! > 0) {
                if (mStatement?.executeUpdate(sql2)!! > 0) {
                    result_code = 0
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result_code
    }


}