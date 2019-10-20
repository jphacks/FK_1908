package jp.hacks.smartbread.ui.aiface

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.hacks.smartbread.databinding.ItemWeatherNewsBinding
import jp.hacks.smartbread.ui.aiface.viewentity.WeatherNewsViewEntity

internal class WeatherNewsViewHodler private constructor(
    private val binding: ItemWeatherNewsBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(
            inflater: LayoutInflater,
            container: ViewGroup
        ): WeatherNewsViewHodler {
            return WeatherNewsViewHodler(
                ItemWeatherNewsBinding.inflate(
                    inflater,
                    container,
                    false
                )
            )
        }
    }

    fun bindTo(newsViewEntity: WeatherNewsViewEntity) {

        binding.bindingModel = newsViewEntity
    }
}