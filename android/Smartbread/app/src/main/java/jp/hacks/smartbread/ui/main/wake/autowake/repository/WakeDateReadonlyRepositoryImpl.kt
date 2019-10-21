package jp.hacks.smartbread.ui.main.wake.autowake.repository

import jp.hacks.smartbread.ui.main.wake.autowake.model.WakeDateModel

internal class WakeDateReadonlyRepositoryImpl :
    WakeDateReadonlyRepository {
    override suspend fun loadWakeDate(): WakeDateModel {
        return WakeDateModel(
            6,
            30
        )
    }
}