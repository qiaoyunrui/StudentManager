package data

/**
 * 成绩数据类
 * Created by qiao1 on 2017/1/1.
 */
class Grade(val studentNo: String,
            val studentName: String,
            val courseNo: String,
            val courseName: String,
            val courseTerm: String,
            val courseDesc: String,
            var courseTeachers: String,
            var score: String) {

    override fun toString(): String {
        return "Grade(studentNo='$studentNo', studentName='$studentName', courseNo='$courseNo', courseName='$courseName', courseTerm='$courseTerm', courseDesc='$courseDesc', courseTeachers='$courseTeachers', score='$score')"
    }
}