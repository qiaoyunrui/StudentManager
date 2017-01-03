package function.adminer.add_course.select_teacher

import data.user.Teacher
import db.DBService
import java.sql.ResultSet
import java.sql.Statement
import java.util.*

/**
 * Created by qiao1 on 2017/1/3.
 */
public class SelectTeacherPresenter {
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

    fun destroy() {
        mDBMS.close()
    }

    var result = Vector<Teacher>()
    /**
     * 查询所有教师
     */
    fun queryAllTeachers(): Vector<Teacher> {
        var sql = "SELECT Tno,Tname,Tsex FROM teacher;"
        result.clear()
        try {
            result = dealResultSet(mStatement?.executeQuery(sql))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    private fun dealResultSet(result_set: ResultSet?): Vector<Teacher> {
        if (result_set == null) {
            return result
        }
        while (result_set.next()) {
            result.add(Teacher(result_set.getString(1),
                    result_set.getString(2),
                    result_set.getString(3)))
        }
        return result
    }

}