package jp.hacks.smartbread.ui.main.weak

import jp.hacks.smartbread.ui.main.weak.model.WeaktimeModel

internal class WeaktimeRepositoryImpl : WeaktimeRepository {
    override suspend fun loadWeakTime(): WeaktimeModel {
        return WeaktimeModel(
            8,
            30
        )
    }

}