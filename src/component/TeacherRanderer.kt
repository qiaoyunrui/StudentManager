package component

import data.user.Teacher
import java.awt.Color
import java.awt.Component
import java.awt.GridLayout
import javax.swing.JLabel
import javax.swing.JList
import javax.swing.JPanel
import javax.swing.ListCellRenderer

/**
 * Created by qiao1 on 2017/1/3.
 */
class TeacherRanderer : JPanel(), ListCellRenderer<Teacher> {

    private var mLabelNo = JLabel()
    private var mLabelName = JLabel()
    private var mLabelSex = JLabel()

    init {
        layout = GridLayout(1, 3, 1, 1)
        add(mLabelNo)
        add(mLabelName)
        add(mLabelSex)
    }

    override fun getListCellRendererComponent(list: JList<out Teacher>, value: Teacher, index: Int,
                                              isSelected: Boolean, cellHasFocus: Boolean): Component? {
        mLabelNo.text = value.no
        mLabelName.text = value.name
        mLabelSex.text = value.sex
        if (isSelected) {
            background = Color(90, 90, 90)
        } else {
            background = Color(255, 255, 255)
        }
        return this
    }

}