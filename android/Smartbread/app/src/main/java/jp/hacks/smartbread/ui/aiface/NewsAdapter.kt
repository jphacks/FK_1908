package jp.hacks.smartbread.ui.aiface

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.hacks.smartbread.ui.aiface.viewentity.NewsViewEntity
import jp.hacks.smartbread.ui.aiface.viewentity.WeatherNewsViewEntity

internal class NewsAdapter(
    private var newsList: List<NewsViewEntity>,
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WeatherNewsViewHodler.create(
            LayoutInflater.from(context),
            parent
        )
    }

    override fun getItemCount(): Int {
        return newsList.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val weatherNewsViewHodler = holder as WeatherNewsViewHodler
        weatherNewsViewHodler.bindTo(newsList.elementAt(position) as WeatherNewsViewEntity)
    }

    fun insert(newsEntityList: List<NewsViewEntity>) {
        newsList = newsEntityList
        notifyDataSetChanged()
    }
}