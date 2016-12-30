package function.adminer.add_teacher

import data.user.Teacher
import db.DBService

import java.sql.Statement

/**
 * Created by qiao1 on 2016/12/30.
 */
class AddTeacherPresenter {

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

    fun closeDB() {
        mDBMS.close()
    }

    /**
     * 注册教师信息
     */
    fun signOn(teacherInfo: Pair<Teacher, String>): Int {
        var sql = "INSERT INTO `teacher` VALUES ('${teacherInfo.first.no}', " +
                "'${teacherInfo.first.name}', " +
                "'${teacherInfo.first.sex}', " +
                "'${teacherInfo.second}');"
        var result_code: Int = 0
        try {
            mStatement?.executeUpdate(sql)
        } catch(e: Exception) {
            e.printStackTrace()
            result_code = -1
        }
        return result_code
    }

}
