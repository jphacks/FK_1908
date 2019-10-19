package jp.hacks.smartbread.ui.main.wake

import com.github.kittinunf.fuel.httpPost

internal class StartBurnBreadUsecaseImpl : StartBurnBreadUsecase {
    override suspend fun execute() {
        val response = "https://maker.ifttt.com/trigger/bread_off/with/key/dbxhWoDInPvF69gTuUdNef"
            .httpPost()
            .response()
    }
}