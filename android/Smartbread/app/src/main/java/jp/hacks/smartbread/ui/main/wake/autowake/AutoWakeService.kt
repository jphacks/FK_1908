package jp.hacks.smartbread.ui.main.wake.autowake

import android.content.Context
import java.util.Date
import java.util.concurrent.TimeUnit
import jp.hacks.smartbread.ui.main.bread.HasBreadUsecase
import jp.hacks.smartbread.ui.main.bread.HasBreadUsecaseImpl
import jp.hacks.smartbread.ui.main.wake.BurnBreadUsecase
import jp.hacks.smartbread.ui.main.wake.BurnBreadUsecaseImpl
import jp.hacks.smartbread.ui.main.wake.autowake.repository.WakeDateReadonlyRepository
import jp.hacks.smartbread.ui.main.wake.autowake.repository.WakeDateReadonlyRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch

internal class AutoWakeService(
    private val context: Context,
    private val wakeDateReadonlyRepository: WakeDateReadonlyRepository = WakeDateReadonlyRepositoryImpl(),
    private val burnBreadUsecase: BurnBreadUsecase = BurnBreadUsecaseImpl(context),
    private val hashBreadUsecase: HasBreadUsecase = HasBreadUsecaseImpl()
) {
    private val interval = TimeUnit.SECONDS.toMillis(45)
    val ticker = ticker(interval)

    init {
        GlobalScope.launch {
            ticker.consumeEach {
                val targetDate = wakeDateReadonlyRepository.loadWakeDate()
                val now = Date()
                // ここを専用タイマークラスに変更する
                if (now.hours == targetDate.hours && now.minutes == targetDate.minutes) {
                    burnBreadUsecase.startBurn()
                }
                if (now.hours == 22 && now.minutes == 0) {
                    GlobalScope.launch(Dispatchers.IO) {
                    }
                }
            }
        }
    }
}
