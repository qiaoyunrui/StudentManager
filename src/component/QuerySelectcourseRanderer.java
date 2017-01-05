package component;

import data.CourseX;

import java.awt.*;

import javax.swing.*;

/**
 * Created by tianlong on 2017/1/4.
 */
public class QuerySelectcourseRanderer extends JPanel implements ListCellRenderer<CourseX> {
    private JLabel mLabelCourseNo;
    private JLabel mLabelCourseName;
    private JLabel mLabelCourseDesc;
    private JLabel mLabelCourseCapacity;
    private JLabel mLabelCourseSelected;
    private JLabel mLabelCourseTerm;
    private JLabel mLabelTeacherName;

    public QuerySelectcourseRanderer() {
        setLayout(new GridLayout(1, 7, 1, 1));
        mLabelCourseNo = new JLabel();
        add(mLabelCourseNo);
        mLabelCourseName = new JLabel();
        add(mLabelCourseName);
        mLabelCourseDesc = new JLabel();
        add(mLabelCourseDesc);
        mLabelCourseCapacity = new JLabel();
        add(mLabelCourseCapacity);
        mLabelCourseSelected = new JLabel();
        add(mLabelCourseSelected);
        mLabelCourseTerm = new JLabel();
        add(mLabelCourseTerm);
        mLabelTeacherName = new JLabel();
        add(mLabelTeacherName);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends CourseX> list, CourseX value, int index, boolean isSelected, boolean cellHasFocus) {
        mLabelCourseNo.setText(value.getNo());
        mLabelCourseName.setText(value.getName());
        mLabelCourseDesc.setText(value.getDesc());
        mLabelCourseCapacity.setText(value.getCapacity() + "");
        mLabelCourseSelected.setText(value.getSelected() + "");
        mLabelCourseTerm.setText(value.getTerm());
        mLabelTeacherName.setText(value.getTeacher());
        if (isSelected) {
            setBackground(new Color(222, 130, 21));
        } else {
            setBackground(new Color(255, 247, 255));
        }
        return this;
    }
}
