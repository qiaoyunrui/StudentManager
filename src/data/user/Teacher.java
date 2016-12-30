package data.user;

import data.user.abs.User;

/**
 * Created by qiao1 on 2016/12/28.
 */
public class Teacher extends User {

    private final String name;

    private String sex;

    public Teacher(String no, String name) {
        super(no);
        this.name = name;
    }

    public Teacher(String no, String name, String sex) {
        this(no, name);
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "no='" + no + '\'' +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
