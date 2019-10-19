package jp.hacks.smartbread.ui.aiface

import androidx.lifecycle.LiveData
import jp.hacks.smartbread.ui.aiface.viewentity.WeatherNewsViewEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

internal class WeatherNerwsLiveData(
    private val coroutineScope: CoroutineScope
) : LiveData<List<WeatherNewsViewEntity>>() {
    init {
        postValue(emptyList())
    }

    fun loadNews() {
        coroutineScope.launch(Dispatchers.IO) {
            Thread.sleep(100)
            if (value == null) {
                value = mutableListOf()
            }

            postValue(
                listOf(
                    WeatherNewsViewEntity(
                        0,
                        "福岡県の天気",
                        "天神",
                        "晴れ",
                        "18℃"
                    )
                )
            )
        }
    }
}