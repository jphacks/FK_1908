package jp.hacks.smartbread.ui.main.wake.toast

import com.github.kittinunf.fuel.httpPost

internal class StopToastUsecaseImpl :
    StopToastUsecase {
    override suspend fun execute() {
        val response = "https://maker.ifttt.com/trigger/bread_off/with/key/dbxhWoDInPvF69gTuUdNef"
            .httpPost()
            .response()
    }
}