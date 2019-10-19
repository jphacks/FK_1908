package jp.hacks.smartbread.ui.main

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.nio.ByteBuffer

internal class ImageCaptureLiveData(
    private val coroutineScope: CoroutineScope,
    private val imageCapture: ImageCapture
) : LiveData<Bitmap>() {
    init {
        coroutineScope.launch(Dispatchers.Unconfined) {
        }
    }

    private fun ByteBuffer.toByteArray(): ByteArray {
        this.rewind()
        val data = ByteArray(remaining())
        this.get(data)
        return data
    }

    fun takePicture() {
        imageCapture.takePicture({
            coroutineScope.launch(Dispatchers.IO) { it.run() }
        }, object : ImageCapture.OnImageCapturedListener() {
            override fun onCaptureSuccess(image: ImageProxy?, rotationDegrees: Int) {
                if (image == null) {
                    return
                }
                val buffer = image.planes.elementAt(0).buffer.toByteArray()
                val bitmap = BitmapFactory.decodeByteArray(buffer, 0, buffer.size)
                postValue(bitmap)

                super.onCaptureSuccess(image, rotationDegrees)
            }
        })
    }
}