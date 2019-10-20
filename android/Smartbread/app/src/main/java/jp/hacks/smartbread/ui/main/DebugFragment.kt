package jp.hacks.smartbread.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jp.hacks.smartbread.MainActivity
import jp.hacks.smartbread.databinding.FragmentDebugBinding
import jp.hacks.smartbread.ui.main.tts.TTSServiceImpl
import jp.hacks.smartbread.ui.main.wake.StartBurnBreadUsecaseImpl
import jp.hacks.smartbread.ui.main.wake.StopBurnBreadUsecaseImpl
import kotlinx.android.synthetic.main.fragment_debug.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

internal class DebugFragment : Fragment() {

    companion object {
        fun newInstance(): DebugFragment {
            return DebugFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDebugBinding.inflate(
            inflater,
            container,
            false
        )

        binding.fragmentDebugPowerOnButton.setOnClickListener {
            val startBurnBreadUsecase = StartBurnBreadUsecaseImpl()
            GlobalScope.launch {
                startBurnBreadUsecase.execute()
            }
        }

        binding.fragmentDebugPowerOffButton.setOnClickListener {
            val stopBurnBreadUsecase = StopBurnBreadUsecaseImpl()
            GlobalScope.launch {
                stopBurnBreadUsecase.execute()
            }
        }

        binding.fragmentDebugTalkButton.setOnClickListener {
            val ttsService = TTSServiceImpl(requireContext())
            GlobalScope.launch {
                ttsService.speach("Hello World")
            }
        }

        binding.fragmentDebugNavigateFaceFragmentButton.setOnClickListener {
            val mainActivity = activity as MainActivity
            mainActivity.navigateToFaceFragment()
        }

        return binding.root
    }
}