package component

import data.MiniGrade
import java.awt.Component
import java.awt.GridLayout
import javax.swing.*

/**
 * Created by qiao1 on 2017/1/3.
 */
class MiniGradeRenderer : JPanel(), ListCellRenderer<MiniGrade> {

    private var mLabelNo = JLabel()
    private var mLabelName: JLabel = JLabel()
    private var mTfScore: JLabel = JLabel()

    init {
        layout = GridLayout(1, 3)
        add(mLabelNo)
        add(mLabelName)
        add(mTfScore)
    }

    override fun getListCellRendererComponent(list: JList<out MiniGrade>?, value: MiniGrade, index: Int, isSelected: Boolean, cellHasFocus: Boolean): Component {
        mLabelNo.text = value.no
        mLabelName.text = value.name
        mTfScore.text = value.score
        return this
    }
}