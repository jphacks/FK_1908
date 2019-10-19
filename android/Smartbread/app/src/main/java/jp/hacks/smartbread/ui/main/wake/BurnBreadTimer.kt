package jp.hacks.smartbread.ui.main.wake

internal interface BurnBreadTimer {
    fun addEvent(minutes: Int, second: Int, event: suspend () -> Unit)
}