package util

import java.awt.Toolkit
import javax.swing.JFrame
import javax.swing.WindowConstants

/**
 * Created by qiao1 on 2016/12/31.
 */

var JFrame.height: Int
    get() = 300
    set(value) {}

var JFrame.width: Int
    get() = 400
    set(value) {}


fun JFrame.init(height: Int, width: Int) {
    setSize(width, height)
    defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    var dimension = Toolkit.getDefaultToolkit().screenSize
    setLocation(dimension.width / 2 - width / 2,
            dimension.height / 2 - height / 2)
    isVisible = true
}

fun JFrame.init() {
    init(height, width)
}
