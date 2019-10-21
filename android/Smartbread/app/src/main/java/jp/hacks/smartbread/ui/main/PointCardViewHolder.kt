package jp.hacks.smartbread.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.hacks.smartbread.databinding.ItemPointcardPointBinding

internal class PointCardViewHolder private constructor(
    private val binding: ItemPointcardPointBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(
            layoutInflater: LayoutInflater,
            container: ViewGroup
        ): PointCardViewHolder {
            return PointCardViewHolder(
                ItemPointcardPointBinding.inflate(
                    layoutInflater,
                    container,
                    false
                )
            )
        }
    }

    fun bindTo(viewEntity: PointCardPointViewEntity) {
        binding.viewEntity = viewEntity
    }
}
