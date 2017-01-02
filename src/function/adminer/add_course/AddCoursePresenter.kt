package function.adminer.add_course

import db.DBService
import java.sql.Statement

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


}