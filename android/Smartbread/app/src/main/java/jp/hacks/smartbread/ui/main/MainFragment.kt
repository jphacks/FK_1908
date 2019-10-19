package jp.hacks.smartbread.ui.main

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Surface
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import jp.hacks.smartbread.R
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.nio.ByteBuffer

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var imageCapture: ImageCapture

    init {
        val imageCaptureConfig = ImageCaptureConfig.Builder()
            .apply {
                setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY)
                setTargetRotation(Surface.ROTATION_180)
            }.build()
        imageCapture = ImageCapture(imageCaptureConfig)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val previewConfig = PreviewConfig.Builder().build()
        val preview = Preview(previewConfig)
        preview.setOnPreviewOutputUpdateListener {
            val previewOutput = it
            view_finder.surfaceTexture = previewOutput.surfaceTexture
        }

        CameraX.bindToLifecycle(this as LifecycleOwner, imageCapture, preview)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    private fun ByteBuffer.toByteArray(): ByteArray {
        rewind()    // Rewind the buffer to zero
        val data = ByteArray(remaining())
        get(data)   // Copy the buffer into a byte array
        return data // Return the byte array
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        main_fragment_pay_meet_button.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this.context, R.raw.cat)
            mediaPlayer.start()
        }

        save_image_button.setOnClickListener {

            imageCapture.takePicture({
                GlobalScope.launch(Dispatchers.IO) {
                    it.run()
                }
            }, object :
                ImageCapture.OnImageCapturedListener() {
                override fun onCaptureSuccess(image: ImageProxy?, rotationDegrees: Int) {


                    if (image == null) {
                        Log.d("error", "image is null")
                        return
                    }

                    val buffer = image.planes.elementAt(0).buffer.toByteArray()


//                val bitmap = Bitmap.createBitmap(image.width, image.height, Bitmap.Config.RGB_565)

                    val bitmap = BitmapFactory.decodeByteArray(buffer, 0, buffer.size)

                    fun Bitmap.RotateBitmap(angle: Float): Bitmap {
                        val matrix = Matrix().apply { postRotate(angle) }
                        return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
                    }

                    GlobalScope.launch(Dispatchers.Main) {
                        saved_image_finder.setImageBitmap(
                            bitmap.RotateBitmap(
                                90F
                            )
                        )
                    }

                    GlobalScope.launch(Dispatchers.Main) { saved_image_finder.setImageBitmap(bitmap) }
                    super.onCaptureSuccess(image, rotationDegrees)
                }

                override fun onError(
                    imageCaptureError: ImageCapture.ImageCaptureError,
                    message: String,
                    cause: Throwable?
                ) {
                    super.onError(imageCaptureError, message, cause)
                }
            })
        }
    }
}
