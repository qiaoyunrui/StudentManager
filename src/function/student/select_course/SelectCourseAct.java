package function.student.select_course;

import org.jetbrains.annotations.NotNull;
import util.JFrameUtilKt;
import util.StackFrame;
import util.JFrameUtilKt;
import util.StackFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

import function.student.select_course.SelectCoursePresenter;
import data.user.Student;
import data.Course;
import db.DBService;

/**
 * Created by tianlong on 2017/1/2.
 */
public class SelectCourseAct extends StackFrame {
    private SelectCoursePresenter mPresenter;
    private JPanel mPanelRoot;
    private JButton mbtnBack;
    private JTextField mTfContent;
    private JList mList;
    private JButton mbtnSearch;
    private Statement statement;
    private Student student;
    private Course course;

    String sterm = student.getTerm();
    String cname = course.getName();

    public SelectCourseAct(SelectCoursePresenter presenter) {
        super("学生选课");
        this.mPresenter = presenter;
        setContentPane(mPanelRoot);
        JFrameUtilKt.init(this, 600, 800);
        initEvent();
    }

    private void initEvent(){
        mbtnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        mbtnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String key = mTfContent.getText();
                if (key != null && !key.trim().equals("")) {
                    Statement statement = mPresenter.getmStatement();
                    try {
                        ResultSet res = statement.executeQuery("SELECT distinct c.Cno,c.Cname,c.Cdesc,c.Ccapacity,c.Cselected,c.Cterm,t.Tname " +
                                "FROM course c, ct a,teacher t where c.Cterm = '"+ sterm + "' AND c.Cno = a.Cno AND a.Tno = t.Tno AND c.Cname like '% "+ cname +"%' ;");
                    } catch (Exception el){
                        el.getMessage();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "请输入内容进行搜索");
                }
            }
        });

        try {
            ResultSet rs = statement.executeQuery("SELECT * from course where Cterm = '" + sterm + "';");

        }catch (Exception el){
            el.getMessage();
        }
    }

    public void onDispose() {
        mPresenter.closeDB();
        super.onDispose();
    }

    public static void main(String[] args) {
        SelectCoursePresenter presenter = new SelectCoursePresenter();
        SelectCourseAct act = new SelectCourseAct(presenter);
    }

}
