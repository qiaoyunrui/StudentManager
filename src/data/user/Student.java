package data.user;

import data.user.abs.User;

/**
 * Created by qiao1 on 2016/12/28.
 */
public class Student extends User {

    private final String name;

    private final String term;

    private String sex;

    public Student(String no, String name, String term) {
        super(no);
        this.name = name;
        this.term = term;
    }

    public Student(String no, String name, String term, String sex) {
        this(no, name, term);
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public String getTerm() {
        return term;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
