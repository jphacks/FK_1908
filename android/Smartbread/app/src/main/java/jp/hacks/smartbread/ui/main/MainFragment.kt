package jp.hacks.smartbread.ui.main

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraX
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import jp.hacks.smartbread.R
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.*
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.request.DataReadRequest
import java.util.concurrent.TimeUnit
import java.util.Locale
import jp.hacks.smartbread.R
import kotlinx.android.synthetic.main.main_fragment.main_fragment_pay_meet_button
import kotlinx.android.synthetic.main.main_fragment.save_image_button
import kotlinx.android.synthetic.main.main_fragment.saved_image_finder
import kotlinx.android.synthetic.main.main_fragment.test_tts_button
import kotlinx.android.synthetic.main.main_fragment.view_finder

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var textToSpeech: TextToSpeech

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        textToSpeech = TextToSpeech(requireContext()) {}
        textToSpeech.language = Locale.JAPANESE

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val TTSViewModel = ViewModelProviders.of(this).get(TTSViewModel::class.java)

        main_fragment_pay_meet_button.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this.context, R.raw.cat)
            mediaPlayer.start()
        }

        save_image_button.setOnClickListener {
            viewModel.takePicture()
        }

        viewModel.imageCaptureLiveData.observeForever {
            saved_image_finder.setImageBitmap(it)
        }

        viewModel.cameraPreviewLiveData.observeForever {
            view_finder.surfaceTexture = it
        }

        test_tts_button.setOnClickListener {
            TTSViewModel.speech("食パン食べたい人はきてね〜、待ってますーす")
        }

        val imageCapture = viewModel.imageCapture
        val preview = viewModel.cameraPreview
        CameraX.bindToLifecycle(this as LifecycleOwner, imageCapture, preview)

        viewModel.startTimer(this.requireContext())
    }


}
