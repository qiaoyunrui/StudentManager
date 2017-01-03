package function.teacher

import data.user.Teacher
import db.DBService
import java.sql.Statement

/**
 * Created by qiao1 on 2017/1/3.
 */
class MainPresenter {

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

    fun changePasswd(teacher_no: String, new_passwd: String): Int {
        var result_code = -1
        var sql = "UPDATE `teacher` SET Tpasswd = '$new_passwd' WHERE Tno = '$teacher_no';"
        try {
            if (mStatement?.executeUpdate(sql)!! > 0) {
                result_code = 0
            }
        } catch(e: Exception) {
            e.printStackTrace()
        }
        return result_code
    }

    fun destroy() {
        mDBMS.close()
    }

}