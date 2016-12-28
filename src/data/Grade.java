package data;

/**
 * Created by qiao1 on 2016/12/28.
 */
public class Grade {

    private final String s_no;  //学生学号

    private final String c_no;  //课程号

    private float score;    //分数

    public Grade(String s_no, String c_no) {
        this.s_no = s_no;
        this.c_no = c_no;
    }

    public Grade(String s_no, String c_no, float score) {
        this(s_no, c_no);
        this.score = score;
    }

    public String getS_no() {
        return s_no;
    }

    public String getC_no() {
        return c_no;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
