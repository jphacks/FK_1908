package jp.hacks.smartbread.ui.main.wake

import java.util.concurrent.TimeUnit
import jp.hacks.smartbread.ui.main.wake.model.BurnBreadEventModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.consumeEachIndexed
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch

internal class BurnBreadTimerImpl(
    private val coroutineScope: CoroutineScope = GlobalScope
) : BurnBreadTimer {
    private var eventList: List<BurnBreadEventModel> = emptyList()
    private val timer = ticker(TimeUnit.SECONDS.toMinutes(1))

    init {
        coroutineScope.launch {
            timer.consumeEachIndexed { item ->
                val index = item.index
                eventList.filter { item ->
                    return@filter (item.minutes * 60 + item.second) == index
                }.forEach {
                    coroutineScope.launch {
                        it.event()
                    }
                }
            }
        }
    }

    override fun addEvent(minutes: Int, second: Int, event: (suspend () -> Unit)) {
        eventList = eventList
            .toMutableList().apply {
                this.add(
                    BurnBreadEventModel(
                        minutes,
                        second,
                        event
                    )
                )
            }
    }
}