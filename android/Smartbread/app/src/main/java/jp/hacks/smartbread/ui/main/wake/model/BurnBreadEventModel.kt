package jp.hacks.smartbread.ui.main.wake.model

internal data class BurnBreadEventModel(
    val minutes: Int,
    val second: Int,
    val event: (suspend () -> Unit)
)