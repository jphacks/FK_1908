package jp.hacks.smartbread.ui.main.pointcard

import jp.hacks.smartbread.ui.main.pointcard.model.PointCardModel

internal interface PointCardReadonlyRepository {
    suspend fun loadPointCard(): PointCardModel
}