package jp.hacks.smartbread.ui.main.wake

internal interface BurnBreadUsecase {
    suspend fun startBurn(): Unit
}