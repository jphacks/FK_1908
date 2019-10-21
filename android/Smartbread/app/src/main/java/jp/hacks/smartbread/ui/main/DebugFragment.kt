package jp.hacks.smartbread.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jp.hacks.smartbread.MainActivity
import jp.hacks.smartbread.databinding.FragmentDebugBinding
import jp.hacks.smartbread.notification.NotificationKind
import jp.hacks.smartbread.notification.SendNotificationUsecaseImpl
import jp.hacks.smartbread.ui.main.tts.TTSServiceImpl
import jp.hacks.smartbread.ui.main.wake.bgm.BurnBreadBGMServiceImpl
import jp.hacks.smartbread.ui.main.wake.toast.StartToastUsecaseImpl
import jp.hacks.smartbread.ui.main.wake.toast.StopToastUsecaseImpl
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
            val startBurnBreadUsecase =
                StartToastUsecaseImpl()
            GlobalScope.launch {
                startBurnBreadUsecase.execute()
            }
        }

        binding.fragmentDebugPowerOffButton.setOnClickListener {
            val stopBurnBreadUsecase =
                StopToastUsecaseImpl()
            GlobalScope.launch {
                stopBurnBreadUsecase.execute()
            }
        }

        binding.fragmentDebugTalkButton.setOnClickListener {
            val ttsService = TTSServiceImpl(requireContext())

            ttsService.speach("今日のお茶汲み係です！！！")
            ttsService.speach("お寿司食べたい！！！！！！！")
        }

        binding.fragmentDebugBgmButton.setOnClickListener {
            val bgmService =
                BurnBreadBGMServiceImpl(requireContext())
            GlobalScope.launch {
                bgmService.startBGM()
            }
        }

        binding.fragmentDebugNavigateFaceFragmentButton.setOnClickListener {
            val mainActivity = activity as MainActivity
            mainActivity.navigateToFaceFragment()
        }

        binding.fragmentDebugShowPointCardButton.setOnClickListener {
            val pointCardDialogFragment = PointCardDialogFragment()
            pointCardDialogFragment.show(requireFragmentManager(), "")
        }

        binding.fragmentDebugSendLocalNotification.setOnClickListener {
            val notificationUsecase = SendNotificationUsecaseImpl(requireContext())
            GlobalScope.launch {
                notificationUsecase.sendNotification(
                    NotificationKind.simple,
                    "Hello Notification"
                )
            }
        }

        return binding.root

    }
}