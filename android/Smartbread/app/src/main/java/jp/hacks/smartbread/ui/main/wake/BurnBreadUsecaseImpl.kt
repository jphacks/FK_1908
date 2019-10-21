package jp.hacks.smartbread.ui.main.wake

import android.content.Context
import jp.hacks.smartbread.ui.main.tts.TTSService
import jp.hacks.smartbread.ui.main.tts.TTSServiceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope

internal class BurnBreadUsecaseImpl(
    private val context: Context,
    private val TTSService: TTSService = TTSServiceImpl(context),
    private val coroutineScope: CoroutineScope = GlobalScope,
    private val burnBreadBGMService: BurnBreadBGMService = BurnBreadBGMServiceImpl(context),
    private val startBurnBreadUsecase: StartBurnBreadUsecase = StartBurnBreadUsecaseImpl(),
    private val stopBurnBreadUsecase: StopBurnBreadUsecase = StopBurnBreadUsecaseImpl(),
    private val burnBreadTimer: BurnBreadTimer = BurnBreadTimerImpl(coroutineScope)
) : BurnBreadUsecase {
    override suspend fun startBurn() {

        val timer = burnBreadTimer

        val debug_mode = false

        if (!debug_mode) {
            // 0:05 に電源を入れる
            timer.addEvent(0, 5) {
                startBurnBreadUsecase.execute()
            }
        }
        // 0:05 に朝だよーって喋る
        timer.addEvent(0, 45) {
            TTSService.speach("朝だから起きて！！！！！")
        }
        // 1:00 に起きてーって言う
        timer.addEvent(1, 0) {
            TTSService.speach("焼きたてパンが待ってるよ！")
        }

        // 2:40 に起きてーって言う
        timer.addEvent(1, 25) {
            TTSService.speach("上手に焼けましたー！！！")
        }

        // 1:30 に BGMをかけ始める
        timer.addEvent(1, 30) {
            burnBreadBGMService.startBGM()
        }

        // 2:30 に 起きろ連呼を終わるまで続ける
        timer.addEvent(1, 40) {
            val text = "起きろ起きろ起きろ"
            val inputText = text + text + text
            TTSService.speach(inputText)
        }

        // 2:45 に電源を落とす
        timer.addEvent(1, 30) {
            stopBurnBreadUsecase.execute()
        }
    }
}
