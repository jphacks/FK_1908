package jp.hacks.smartbread.ui.main.wake

import jp.hacks.smartbread.ui.main.wake.model.WeaktimeModel

internal interface WeaktimeRepository {
    suspend fun loadWeakTime(): WeaktimeModel
}