package data.user.abs;

/**
 * Created by qiao1 on 2016/12/28.
 */
public abstract class User {

    protected final String no;

    public User(String no) {
        this.no = no;
    }

    public String getNo() {
        return no;
    }

}
