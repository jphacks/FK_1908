package jp.hacks.smartbread.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import jp.hacks.smartbread.databinding.DialogFragmentPointCardBinding

internal class PointCardDialogFragment : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DialogFragmentPointCardBinding
            .inflate(
                layoutInflater,
                container,
                false
            )

        val pointCardAdpater = PointCardAdapter(requireContext())
        pointCardAdpater.submitList(
            listOf(
                PointCardPointViewEntity(
                    0,
                    "5P",
                    "2019/10/10"
                ),
                PointCardPointViewEntity(
                    1,
                    "10P",
                    "2019/10/10"
                )
            )
        )

        binding.dialogFragmentPointCardPointCardRecyclerView.adapter = pointCardAdpater

        return binding.root
    }
}