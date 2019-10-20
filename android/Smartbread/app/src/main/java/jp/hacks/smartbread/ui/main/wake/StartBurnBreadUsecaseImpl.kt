package jp.hacks.smartbread.ui.main.wake

import com.github.kittinunf.fuel.httpPost

internal class StartBurnBreadUsecaseImpl : StartBurnBreadUsecase {
    override suspend fun execute() {
        val response = "https://maker.ifttt.com/trigger/bread_on/with/key/dbxhWoDInPvF69gTuUdNef"
            .httpPost()
            .response()
        val result = response.first
    }
}