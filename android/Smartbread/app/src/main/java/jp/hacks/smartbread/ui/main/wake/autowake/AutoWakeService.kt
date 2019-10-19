package jp.hacks.smartbread.ui.main.wake.autowake

import android.content.Context
import java.util.*
import java.util.concurrent.TimeUnit
import jp.hacks.smartbread.ui.main.wake.BurnBreadUsecase
import jp.hacks.smartbread.ui.main.wake.BurnBreadUsecaseImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch

internal class AutoWakeService(
    private val context: Context,
    private val wakeDateReadonlyRepository: WakeDateReadonlyRepository = WakeDateReadonlyRepositoryImpl(),
    private val burnBreadUsecase: BurnBreadUsecase = BurnBreadUsecaseImpl(context)
) {
    private val interval = TimeUnit.SECONDS.toMillis(45)
    val ticker = ticker(interval)

    init {
        GlobalScope.launch {
            ticker.consumeEach {
                val targetDate = wakeDateReadonlyRepository.loadWakeDate()
                val now = Date()
                if (now.hours == targetDate.hours && now.minutes == targetDate.minutes) {
                    burnBreadUsecase.startBurn()
                }
            }
        }
    }
}
