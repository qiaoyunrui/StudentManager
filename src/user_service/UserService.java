package user_service;

import data.user.Student;
import data.user.abs.User;

/**
 * Created by qiao1 on 2016/12/28.
 */
public class UserService {

    private final static int TYPE_NONE = 0x03;
    private final static int TYPE_STUDENT = 0x00;
    private final static int TYPE_TEACHER = 0x01;
    private final static int TYPE_ADMINER = 0x02;

    private static final class UserServiceHolder {
        private static UserService sInstance = new UserService();
    }

    public static UserService getInstance() {
        return UserServiceHolder.sInstance;
    }

    private User currentUser;   //当前登录的用户

    private int userType = TYPE_NONE;   //用户类型

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    /**
     * 获取当前登录用户
     */
    public User getCurrentUser() {
        return currentUser;
    }

    public void signIn(User user) {
        this.currentUser = user;
    }

    public void signOut() {
        currentUser = null;
        userType = TYPE_NONE;
    }

}
