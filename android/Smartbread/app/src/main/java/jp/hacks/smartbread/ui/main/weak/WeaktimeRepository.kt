package jp.hacks.smartbread.ui.main.weak

import jp.hacks.smartbread.ui.main.weak.model.WeaktimeModel

internal interface WeaktimeRepository {
    suspend fun loadWeakTime(): WeaktimeModel
}