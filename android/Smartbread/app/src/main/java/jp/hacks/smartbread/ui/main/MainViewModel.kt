package jp.hacks.smartbread.ui.main

import android.content.Context
import android.view.Surface
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureConfig
import androidx.camera.core.Preview
import androidx.camera.core.PreviewConfig
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.hacks.smartbread.ui.main.wake.autowake.AutoWakeService

internal class MainViewModel : ViewModel() {
    lateinit var imageCapture: ImageCapture
    lateinit var cameraPreview: Preview

    lateinit var cameraPreviewLiveData: CameraPreviewLiveData
    lateinit var imageCaptureLiveData: ImageCaptureLiveData
    private lateinit var autoWakeService: AutoWakeService

    init {
        val imageCaptureConfig = ImageCaptureConfig.Builder()
            .apply {
                setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY)
                setTargetRotation(Surface.ROTATION_180)
            }.build()
        imageCapture = ImageCapture(imageCaptureConfig)

        val previewConfig = PreviewConfig.Builder().build()
        cameraPreview = Preview(previewConfig)

        cameraPreviewLiveData = CameraPreviewLiveData(viewModelScope, cameraPreview)
        imageCaptureLiveData = ImageCaptureLiveData(viewModelScope, imageCapture)
    }

    fun takePicture() {
        imageCaptureLiveData.takePicture()
    }

    fun startTimer(context: Context) {
        autoWakeService = AutoWakeService(context)
    }
}
