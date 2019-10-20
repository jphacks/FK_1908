package jp.hacks.smartbread.ui.main.wake

import android.util.Log
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
    private val timer = ticker(TimeUnit.SECONDS.toMillis(1))

    init {
        Log.d("burnbread", "timer started")

        coroutineScope.launch {
            timer.consumeEachIndexed { item ->
                Log.d("burnbread", item.index.toString())
                val index = item.index
                eventList.filter { item ->
                    Log.d("burnbread", "min: ${TimeUnit.MINUTES.toSeconds(item.minutes.toLong())} sec: ${item.second.toLong()}")
                    return@filter (TimeUnit.MINUTES.toSeconds(item.minutes.toLong()) + item.second.toLong()) == index.toLong()
                }.forEach {
                    coroutineScope.launch {
                        Log.d("burnbread", "${it.minutes}: ${it.second}")
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