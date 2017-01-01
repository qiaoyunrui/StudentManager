package util

import java.util.*
import javax.swing.JFrame

/**
 * 使用FrameStack来管理JFrame的状态
 *
 * Created by qiao1 on 2017/1/1.
 */
open abstract class StackFrame : JFrame {

    constructor(title: String) : super(title)

    companion object {
        private val mFrameStack: Stack<StackFrame> = Stack<StackFrame>()
    }

    init {
        mFrameStack.add(this)
        onStart()
        mFrameStack.forEach { it.onOtherStart(this) }
    }

    /**
     * 关闭本界面，并出栈
     */
    override fun dispose() {
        mFrameStack.pop()
        onDispose()
        mFrameStack.forEach { it.onOtherDispose(this) }  //遍历通知关闭
        super.dispose()
    }

    open fun onStart() {}

    open fun onOtherStart(stackFrame: StackFrame) {}

    /**
     * 当自己关闭的时候调用
     */
    open fun onDispose() {
    }

    /**
     * 当其他Frame关闭时
     */
    open fun onOtherDispose(stackFrame: StackFrame) {
    }

    /**
     * 提交信息
     */
    open fun submitData(submit_code: Int, data: Any) {
        mFrameStack.forEach { it.onSubmitData(submit_code, data, it) }
    }

    open fun onSubmitData(submit_code: Int, data: Any, submiter: StackFrame) {}

    override fun hide() {
        super.hide()
        mFrameStack.forEach { it.onOtherHide(it) }
    }

    open fun onOtherHide(hider: StackFrame) {}

    override fun show() {
        super.show()
        mFrameStack.forEach { it.onOtherHide(it) }
    }

    open fun onOtherShow(shower: StackFrame) {}

}