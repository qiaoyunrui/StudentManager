package component

import data.MiniCourse
import java.awt.Component
import java.awt.GridLayout
import javax.swing.JLabel
import javax.swing.JList
import javax.swing.JPanel
import javax.swing.ListCellRenderer

/**
 * Created by qiao1 on 2017/1/3.
 */
class MiniCourseRenderer : JPanel(), ListCellRenderer<MiniCourse> {

    private var mLabelNo: JLabel = JLabel()
    private var mLabelName: JLabel = JLabel()

    init {
        layout = GridLayout(1, 2)
        add(mLabelNo)
        add(mLabelName)
    }

    override fun getListCellRendererComponent(list: JList<out MiniCourse>?, value: MiniCourse,
                                              index: Int, isSelected: Boolean, cellHasFocus: Boolean)
            : Component {
        mLabelNo.text = value.no
        mLabelName.text = value.name
        return this
    }
}