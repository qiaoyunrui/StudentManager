package signIn;

import data.user.Adminer;
import data.user.Student;
import data.user.Teacher;
import db.DBService;
import function.adminer.MainForm;
import function.student.person.SwingTest;
import function.teacher.Main;
import function.teacher.MainPresenter;
import org.jetbrains.annotations.NotNull;
import user_service.UserService;
import util.JFrameUtilKt;
import util.StackFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.sql.*;

public class SignInAct extends StackFrame {
    public static JLabel welcome_label, username_label, password_label;
    public static JButton enter, close;
    public static JTextField username;
    public static JPasswordField password;

    private JRadioButton student_btn;
    private JRadioButton teacher_btn;
    private JRadioButton adminer_btn;

    private ButtonGroup button_group;

    private Statement mStatement;

    private StackFrame adminFrame;
    private StackFrame teacherFrame;

    public SignInAct() {
        super("登录");
        welcome_label = new JLabel("欢迎进入学生成绩管理系统");
        username_label = new JLabel("用户名");
        password_label = new JLabel("密码");
        username = new JTextField(12);
        password = new JPasswordField(12);
        enter = new JButton("确认");
        close = new JButton("关闭");

        teacher_btn = new JRadioButton("教师");
        student_btn = new JRadioButton("学生");
        adminer_btn = new JRadioButton("教务处");

        setLayout(new BorderLayout());
        add(welcome_label, BorderLayout.NORTH);


        Panel username_panel = new Panel();
        username_panel.setLayout(new FlowLayout());
        username_panel.add(username_label);
        username_panel.add(username);
        Panel centerPanel = new Panel();
        centerPanel.setLayout(new GridLayout(2, 1));
        centerPanel.add(username_panel);
        Panel passwd_panel = new Panel();
        passwd_panel.setLayout(new FlowLayout());
        passwd_panel.add(password_label);
        passwd_panel.add(password);
        centerPanel.add(passwd_panel);
        add(centerPanel, BorderLayout.CENTER);

        Panel button_panel = new Panel();
        button_panel.setLayout(new FlowLayout());
        button_panel.add(enter);
        button_panel.add(close);
        add(button_panel, BorderLayout.SOUTH);

        Panel user_panel = new Panel();
        user_panel.setLayout(new GridLayout(3, 1));
        user_panel.add(teacher_btn);
        user_panel.add(student_btn);
        user_panel.add(adminer_btn);

        button_group = new ButtonGroup();
        button_group.add(teacher_btn);
        button_group.add(student_btn);
        button_group.add(adminer_btn);


        add(user_panel, BorderLayout.WEST);
        JFrameUtilKt.init(this, 200, 400);
        initEvent();
        try {
            DBService.getInstance().connect();
            mStatement = DBService.getInstance().getStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initEvent() {
        // TODO Auto-generated method stub
        close.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                dispose();//close
            }
        });
        enter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String username_str = username.getText();
                String passwd_str = password.getText();
                int type = 0;
                if (student_btn.isSelected()) {
                    type = 1;
                }
                if (teacher_btn.isSelected()) {
                    type = 2;
                }
                if (adminer_btn.isSelected()) {
                    type = 3;
                }
                signIn(username_str, passwd_str, type);
            }
        });
    }

    private void signIn(String username, String passwd, int type) {
        if (username == null || username.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "用户名不能为空");
            return;
        }
        if (passwd == null || passwd.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "密码不能为空");
            return;
        }

        String sql = "";

        switch (type) {
            case 1:
                sql = "SElECT Sno,Sname,Ssex,Sterm,Spasswd FROM student WHERE Sno = '" + username + "';";
                try {
                    ResultSet resultSet = mStatement.executeQuery(sql);
                    if (resultSet.next()) {
                        if (resultSet.getString(5).equals(passwd)) {
                            Student student = new Student(resultSet.getString(1),
                                    resultSet.getString(2),
                                    resultSet.getString(4),
                                    resultSet.getString(3));
                            UserService.getInstance().signIn(student);
                            UserService.getInstance().setUserType(UserService.TYPE_STUDENT);
                            dispose();
                            new SwingTest();
                            return;
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                sql = "SElECT Tno,Tname,Tsex,Tpasswd FROM teacher WHERE Tno = '" + username + "';";
                try {
                    ResultSet resultSet = mStatement.executeQuery(sql);
                    if (resultSet.next()) {
                        if (resultSet.getString(4).equals(passwd)) {
                            Teacher teacher = new Teacher(resultSet.getString(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3));
                            UserService.getInstance().signIn(teacher);
                            UserService.getInstance().setUserType(UserService.TYPE_TEACHER);
                            hide();
                            teacherFrame = new Main(new MainPresenter());
                            return;
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                sql = "SELECT Ano,Apasswd FROM administrator WHERE Ano = '" + username + "';";
                try {
                    ResultSet resultSet = mStatement.executeQuery(sql);
                    if (resultSet.next()) {
                        if (resultSet.getString(2).equals(passwd)) {
                            Adminer adminer = new Adminer(resultSet.getString(1));
                            UserService.getInstance().signIn(adminer);
                            UserService.getInstance().setUserType(UserService.TYPE_ADMINER);
                            hide();
                            adminFrame = new MainForm();
                            return;
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                JOptionPane.showMessageDialog(this, "请选择登录类型");
                break;
        }
        JOptionPane.showMessageDialog(this, "登录失败");
    }

    @Override
    public void onOtherDispose(@NotNull StackFrame stackFrame) {
        super.onOtherDispose(stackFrame);
        if (stackFrame == adminFrame) {
            show();
        }
        if (stackFrame == teacherFrame) {
            show();
        }
    }
}
