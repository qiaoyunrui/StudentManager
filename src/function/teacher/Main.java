package function.teacher;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import data.user.Teacher;
import user_service.UserService;
import util.JFrameUtilKt;
import util.StackFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by qiao1 on 2017/1/3.
 */
public class Main extends StackFrame {
    private JPanel mPanelRoot;
    private JButton mBtnInputGrade;
    private JButton mBtnQueryGrade;
    private JButton mBtnSignOut;
    private JButton mBtnChangePasswd;
    private JLabel mLabelNo;
    private JLabel mLabelName;
    private JLabel mLabelSex;

    private MainPresenter mPresenter;
    private UserService mService;
    private Teacher teacher;

    public Main(MainPresenter presenter) {
        super("学生成绩管理系统-教师");
        this.mPresenter = presenter;
        setContentPane(mPanelRoot);
        JFrameUtilKt.init(this);
        initData();
        initEvent();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mService = UserService.getInstance();
        if (mService.getUserType() == UserService.TYPE_TEACHER) {   //如果登录类型是教师的话
            showTeacher((Teacher) mService.getCurrentUser());
        }
    }

    private void initEvent() {
        mBtnChangePasswd.addActionListener(l -> {
            String new_passwd = JOptionPane.showInputDialog("请输入新的密码：");
            if (new_passwd == null) {
                return;
            }
            if (mPresenter.changePasswd(mService.getCurrentUser().getNo(), new_passwd) == 0) {
                JOptionPane.showMessageDialog(this, "修改密码成功！");
            } else {
                JOptionPane.showMessageDialog(this, "修改密码失败！");
            }
        });
        mBtnSignOut.addActionListener(l -> {
            dispose();
        });
        mBtnQueryGrade.addActionListener(l -> {

        });
        mBtnInputGrade.addActionListener(l -> {
            turn2InputGradeAct();
        });
    }

    private void turn2InputGradeAct() {

    }

    private void showTeacher(Teacher teacher) {
        mLabelNo.setText(teacher.getNo());
        mLabelName.setText(teacher.getName());
        mLabelSex.setText(teacher.getSex());
    }

    public static void main(String[] args) {
        UserService.getInstance().signIn(new Teacher("1407064301", "居合子", "男"));
        UserService.getInstance().setUserType(UserService.TYPE_TEACHER);
        new Main(new MainPresenter());
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mPanelRoot = new JPanel();
        mPanelRoot.setLayout(new GridLayoutManager(1, 2, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        mPanelRoot.add(panel1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        mBtnInputGrade = new JButton();
        mBtnInputGrade.setFont(new Font(mBtnInputGrade.getFont().getName(), mBtnInputGrade.getFont().getStyle(), 18));
        mBtnInputGrade.setText("录入成绩");
        panel1.add(mBtnInputGrade, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mBtnQueryGrade = new JButton();
        mBtnQueryGrade.setFont(new Font(mBtnQueryGrade.getFont().getName(), mBtnQueryGrade.getFont().getStyle(), 18));
        mBtnQueryGrade.setText("查询/修改成绩");
        panel1.add(mBtnQueryGrade, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mBtnSignOut = new JButton();
        mBtnSignOut.setFont(new Font(mBtnSignOut.getFont().getName(), mBtnSignOut.getFont().getStyle(), 18));
        mBtnSignOut.setText("退出登录");
        panel1.add(mBtnSignOut, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mBtnChangePasswd = new JButton();
        mBtnChangePasswd.setFont(new Font(mBtnChangePasswd.getFont().getName(), mBtnChangePasswd.getFont().getStyle(), 18));
        mBtnChangePasswd.setText("修改密码");
        panel1.add(mBtnChangePasswd, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        mPanelRoot.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setFont(new Font(label1.getFont().getName(), label1.getFont().getStyle(), 18));
        label1.setText("欢迎你，教师。");
        panel2.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setFont(new Font(label2.getFont().getName(), label2.getFont().getStyle(), 18));
        label2.setText("教师号");
        panel3.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mLabelNo = new JLabel();
        mLabelNo.setFont(new Font(mLabelNo.getFont().getName(), mLabelNo.getFont().getStyle(), 18));
        mLabelNo.setText("");
        panel3.add(mLabelNo, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setFont(new Font(label3.getFont().getName(), label3.getFont().getStyle(), 18));
        label3.setText("性别");
        panel4.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mLabelSex = new JLabel();
        mLabelSex.setFont(new Font(mLabelSex.getFont().getName(), mLabelSex.getFont().getStyle(), 18));
        mLabelSex.setText("");
        panel4.add(mLabelSex, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel5, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setFont(new Font(label4.getFont().getName(), label4.getFont().getStyle(), 18));
        label4.setText("教师姓名");
        panel5.add(label4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mLabelName = new JLabel();
        mLabelName.setFont(new Font(mLabelName.getFont().getName(), mLabelName.getFont().getStyle(), 18));
        mLabelName.setText("");
        panel5.add(mLabelName, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mPanelRoot;
    }
}
