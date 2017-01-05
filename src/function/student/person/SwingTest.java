package function.student.person;

import data.user.Student;
import util.JFrameUtilKt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingTest {
    JFrame f = new JFrame("学生基本信息");
    JLabel sno = new JLabel("学号：");
    JLabel sname = new JLabel("姓名：");
    JLabel ssex = new JLabel("性别：");
    JLabel sterm = new JLabel("学期：");
    JButton res = new JButton("修改密码");
    JButton ret = new JButton("返回");
    JLabel bepass = new JLabel("原密码：");
    JLabel afpass = new JLabel("新密码：");
    JLabel afpass2 = new JLabel("再次输入新密码：");
    JTextField bepasswd = new JTextField(20);
    JTextField afpasswd = new JTextField(20);
    JTextField afpasswd2 = new JTextField(20);
    JButton done = new JButton("完成");
    JDialog d = new JDialog(f, "修改密码", true);


    public SwingTest() {
        init();
        initData();
    }

    private SelectStu selectStu = new SelectStu();
    ;

    private void initData() {
        showData(selectStu.getStudentInfo("1407064241"));
    }

    private void showData(Student student) {
        if (student == null) return;
        sno.setText(sno.getText() + student.getNo());
        sname.setText(sname.getText() + student.getName());
        sterm.setText(sterm.getText() + student.getSex());
        ssex.setText(ssex.getText() + student.getTerm());
    }

    public void init() {
        d.setBounds(320, 30, 300, 400);
        sno.setFont(new java.awt.Font("Dialog", 1, 35));
        sname.setFont(new java.awt.Font("Dialog", 1, 35));
        ssex.setFont(new java.awt.Font("Dialog", 1, 35));
        sterm.setFont(new java.awt.Font("Dialog", 1, 35));
        JPanel bottom = new JPanel();
        bottom.add(sno);
        bottom.add(sname);
        bottom.add(ssex);
        bottom.add(sterm);
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        JPanel bottom2 = new JPanel();
        res.addActionListener(e -> d.setVisible(true));
        bottom2.add(res);
        bottom2.add(ret);
        f.add(bottom, BorderLayout.NORTH);
        f.add(bottom2, BorderLayout.SOUTH);
        JPanel bottom3 = new JPanel();
        bottom3.add(bepass);
        bottom3.add(bepasswd);
        bottom3.add(afpass);
        bottom3.add(afpasswd);
        bottom3.add(afpass2);
        bottom3.add(afpasswd2);
        d.add(bottom3);
        d.add(done, BorderLayout.SOUTH);
        ret.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });
        done.addActionListener(e -> {
            String old_passwd = bepasswd.getText();
            String new_passwd = afpasswd.getText();
            String new_passwd_a = afpasswd2.getText();
            if (old_passwd == null || old_passwd.trim().equals("") ||
                    new_passwd == null || new_passwd.trim().equals("") ||
                    new_passwd_a == null || new_passwd_a.trim().equals("") ||
                    !new_passwd.equals(new_passwd_a)) {
                JOptionPane.showMessageDialog(null, "无法修改密码！");
            }
            if (selectStu.changePasswd("1407064241", old_passwd, new_passwd) == 0) {
                JOptionPane.showMessageDialog(null, "修改密码成功！");
            } else {
                JOptionPane.showMessageDialog(null, "修改密码失败！");
            }

        });
        JFrameUtilKt.init(f, 400, 400);
    }

    public static void main(String[] args) {
        new SwingTest();
    }


}
