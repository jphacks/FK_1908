package jp.hacks.smartbread.ui.main

import android.graphics.SurfaceTexture
import androidx.camera.core.Preview
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class CameraPreviewLiveData(
    coroutineScope: CoroutineScope,
    private val cameraPreview: Preview
) : LiveData<SurfaceTexture>() {
    init {
        coroutineScope.launch(Dispatchers.Unconfined) {
            cameraPreview.setOnPreviewOutputUpdateListener {
                postValue(it.surfaceTexture)
            }
        }
    }
}