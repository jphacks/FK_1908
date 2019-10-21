package jp.hacks.smartbread.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

internal class PointCardAdapter(
    private val context: Context
) : ListAdapter<PointCardPointViewEntity, PointCardViewHolder>(DIFF_UTIL) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointCardViewHolder {
        return PointCardViewHolder.create(
            LayoutInflater.from(context),
            parent
        )
    }

    override fun onBindViewHolder(holder: PointCardViewHolder, position: Int) {
        return holder.bindTo(getItem(position))
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<PointCardPointViewEntity>() {
            override fun areItemsTheSame(
                oldItem: PointCardPointViewEntity,
                newItem: PointCardPointViewEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: PointCardPointViewEntity,
                newItem: PointCardPointViewEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}