package util

/**
 * Created by qiao1 on 2017/1/1.
 */
interface Action<T> {
    fun onAction(t: T)
}