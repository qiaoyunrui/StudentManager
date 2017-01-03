package component;

import data.Grade;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;

/**
 * Created by qiao1 on 2017/1/2.
 */
public class QueryGradeRanderer extends JPanel implements ListCellRenderer<Grade> {
    private JLabel mLabelStudentNo;
    private JLabel mLabelStudentName;
    private JLabel mLabelCourseNo;
    private JLabel mLabelCourseName;
    private JLabel mLabelCourseTerm;
    private JLabel mLabelCourseDesc;
    private JLabel mLabelTeachers;
    private JLabel mLabelScore;

    public QueryGradeRanderer() {
        setLayout(new GridLayout(1, 8, 1, 1));
        mLabelStudentNo = new JLabel();
        add(mLabelStudentNo);
        mLabelStudentName = new JLabel();
        add(mLabelStudentName);
        mLabelCourseNo = new JLabel();
        add(mLabelCourseNo);
        mLabelCourseName = new JLabel();
        add(mLabelCourseName);
        mLabelCourseTerm = new JLabel();
        add(mLabelCourseTerm);
        mLabelCourseDesc = new JLabel();
        add(mLabelCourseDesc);
        mLabelTeachers = new JLabel();
        add(mLabelTeachers);
        mLabelScore = new JLabel();
        add(mLabelScore);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Grade> list, Grade value, int index, boolean isSelected, boolean cellHasFocus) {
        mLabelStudentNo.setText(value.getStudentNo());
        mLabelStudentName.setText(value.getStudentName());
        mLabelCourseNo.setText(value.getCourseNo());
        mLabelCourseName.setText(value.getCourseName());
        mLabelCourseTerm.setText(value.getCourseTerm());
        mLabelCourseDesc.setText(value.getCourseDesc());
        mLabelTeachers.setText(value.getCourseTeachers());
        mLabelScore.setText(value.getScore());
        if (isSelected) {
            setBackground(new Color(24, 90, 83));
        } else {
            setBackground(new Color(255, 247, 255));
        }
        return this;
    }
}
