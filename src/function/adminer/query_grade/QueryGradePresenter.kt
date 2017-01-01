package function.adminer.query_grade

import data.Grade
import db.DBService
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

    fun search(key: String): Vector<Grade> {

        return result
    }

    fun closeDB() {
        mDBMS.close()
    }

}