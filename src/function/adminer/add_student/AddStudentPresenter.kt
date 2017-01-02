package function.adminer.add_student

import data.user.Student
import db.DBService
import java.sql.Statement

/**
 * Created by qiao1 on 2016/12/30.
 */
class AddStudentPresenter {

    private val mDBMS: DBService by lazy { DBService.getInstance() }
    private var mStatement: Statement? = null

    init {
        try {
            mDBMS.connect()
            mStatement = mDBMS.statement
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }

    fun closeDB() {
        mDBMS.close()
    }

    /**
     * 注册学生信息
     */
    fun signOn(studentInfo: Pair<Student, String>): Int {
        var sql = "INSERT INTO `student` VALUE " +
                "('${studentInfo.first.no}'," +
                "'${studentInfo.first.name}'," +
                "'${studentInfo.first.sex}'," +
                "'${studentInfo.second}'," +
                "'${studentInfo.first.term}');"
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