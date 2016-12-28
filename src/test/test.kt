package test

import db.DBService

/**
 * Created by qiao1 on 2016/12/28.
 */
fun main(args: Array<String>) {
    var dbms = DBService()
    dbms.connect()
    var stme = dbms.statement
    val result = stme.execute("SELECT * FROM Student")
    println(result == null)
}