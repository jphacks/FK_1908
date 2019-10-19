package jp.hacks.smartbread.ui.main

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraX
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import jp.hacks.smartbread.R
import kotlinx.android.synthetic.main.main_fragment.*
import java.nio.ByteBuffer

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

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

        val imageCapture = viewModel.imageCapture
        val preview = viewModel.cameraPreview
        CameraX.bindToLifecycle(this as LifecycleOwner, imageCapture, preview)
    }
}
