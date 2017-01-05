package function.student.main.selectedcourse;

import component.QuerySelectcourseRanderer;
import data.CourseX;
import util.JFrameUtilKt;
import util.StackFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Created by tianlong on 2017/1/4.
 */
public class QuerySelectcourseAct extends StackFrame {
    private QuerySelectcoursePresenter mPresenter;
    private JPanel mPanelRoot;
    private JTextField mTfContent;
    private JButton mBtnSearch;
    private JButton mBtnBack;
    private JList<CourseX> mList;
    private String key;

    private Vector mData;

    public QuerySelectcourseAct(QuerySelectcoursePresenter presenter) {
        super("学生选课");
        this.mPresenter = presenter;
        setContentPane(mPanelRoot);
        JFrameUtilKt.init(this, 600, 800);
        initEvent();
    }

    private void initEvent() {
        mList.setFixedCellWidth(getWidth());
        mList.setFixedCellHeight(getHeight() / 20);
        mList.setCellRenderer(new QuerySelectcourseRanderer());
        mBtnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        mBtnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                key = mTfContent.getText();
                if (key != null && !key.trim().equals("")) {
                    setData(mPresenter.search(key));
                } else {
                    JOptionPane.showMessageDialog(null, "请输入内容进行搜素");
                }
            }
        });
        mList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (mList.getSelectedIndex() != -1) {
                    if (e.getClickCount() == 2) {
                        if (mData != null) {

                        }
                    }
                }
            }
        });
    }

    public void setData(Vector data) {
        mData = data;
        mList.setListData(data);
    }

    public void onDispose() {
        mPresenter.closeDB();
        super.onDispose();
    }

    public static void main(String[] args) {
        QuerySelectcoursePresenter presenter = new QuerySelectcoursePresenter();
        QuerySelectcourseAct act = new QuerySelectcourseAct(presenter);
    }
}
