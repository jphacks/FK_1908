package jp.hacks.smartbread.ui.aiface

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FaceViewModel : ViewModel() {
    private val modifiableCountEye: MutableLiveData<Int> = MutableLiveData()
    val countEye: LiveData<Int> = modifiableCountEye

    fun addCount() {
        modifiableCountEye.value = (countEye.value ?: 0) + 1
    }
}
