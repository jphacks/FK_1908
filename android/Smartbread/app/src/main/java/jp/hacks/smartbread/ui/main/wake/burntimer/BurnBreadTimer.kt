package jp.hacks.smartbread.ui.main.wake.burntimer

internal interface BurnBreadTimer {
    fun addEvent(minutes: Int, second: Int, event: suspend () -> Unit)
}