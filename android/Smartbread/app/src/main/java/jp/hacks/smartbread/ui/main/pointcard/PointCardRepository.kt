package jp.hacks.smartbread.ui.main.pointcard

import jp.hacks.smartbread.ui.main.pointcard.model.Point

internal interface PointCardRepository {
    suspend fun addPoint(point: Point)
    suspend fun removeAllPoint()
}