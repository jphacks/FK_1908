package jp.hacks.smartbread.ui.main.wake.autowake.repository

import jp.hacks.smartbread.ui.main.wake.autowake.model.WakeDateModel

internal interface WakeDateReadonlyRepository {
    suspend fun loadWakeDate(): WakeDateModel
}