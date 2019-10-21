package jp.hacks.smartbread.ui.main.wake.toast

import com.github.kittinunf.fuel.httpPost

internal class StartToastUsecaseImpl :
    StartToastUsecase {
    override suspend fun execute() {
        val response = "https://maker.ifttt.com/trigger/bread_on/with/key/dbxhWoDInPvF69gTuUdNef"
            .httpPost()
            .response()
        val result = response.first
    }
}