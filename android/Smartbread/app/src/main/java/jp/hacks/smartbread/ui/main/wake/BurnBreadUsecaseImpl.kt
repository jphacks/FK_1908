package jp.hacks.smartbread.ui.main.wake

import android.content.Context
import jp.hacks.smartbread.ui.main.tts.TTSService
import jp.hacks.smartbread.ui.main.tts.TTSServiceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

internal class BurnBreadUsecaseImpl(
    private val context: Context,
    private val coroutineScope: CoroutineScope = GlobalScope,
    private val burnBreadBGMService: BurnBreadBGMService = BurnBreadBGMServiceImpl(context),
    private val startBurnBreadUsecase: StartBurnBreadUsecase = StartBurnBreadUsecaseImpl(),
    private val stopBurnBreadUsecase: StopBurnBreadUsecase = StopBurnBreadUsecaseImpl(),
    private val TTSService: TTSService = TTSServiceImpl(context),
    private val burnBreadTimer: BurnBreadTimer = BurnBreadTimerImpl(coroutineScope)
) : BurnBreadUsecase {
    override suspend fun startBurn() {
        coroutineScope.launch {
            val timer = burnBreadTimer
            // 0:05 に電源を入れる
            timer.addEvent(0, 5) {
                startBurnBreadUsecase.execute()
            }
            // 0:05 に朝だよーって喋る
            timer.addEvent(0, 5) {
                TTSService.speach("朝だよー！")
            }
            // 2:00 に起きてーって言う
            timer.addEvent(2, 0) {
                TTSService.speach("起きてー！")
            }

            // 2:30 に BGMをかけ始める
            timer.addEvent(2, 30) {
                burnBreadBGMService.startBGM()
            }

            // 2:30 に 起きろ連呼を終わるまで続ける
            timer.addEvent(2, 30) {
                val text = "起きろ起きろ起きろ"
                val inputText = text + text + text
                TTSService.speach(inputText)
            }

            // 2:40 に起きてーって言う
            timer.addEvent(2, 0) {
                TTSService.speach("上手に焼けましたー！！！")
            }

            // 2:45 に電源を落とす
            timer.addEvent(2, 30) {
                stopBurnBreadUsecase.execute()
            }
        }
    }
}