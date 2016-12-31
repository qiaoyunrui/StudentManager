package function.adminer.add_student

import db.DBService
import java.sql.Statement

/**
 * Created by qiao1 on 2016/12/30.
 */
class AddStudentPresenter {

    private val mDBMS: DBService by lazy { DBService.getInstance() }
    private var mSatement: Statement? = null

    init {
        try {
            mDBMS.connect()
            mSatement = mDBMS.statement
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }

    fun closeDB() {
        mDBMS.close()
    }

}