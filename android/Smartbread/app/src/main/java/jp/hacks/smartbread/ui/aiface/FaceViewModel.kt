package jp.hacks.smartbread.ui.aiface

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FaceViewModel : ViewModel() {
    private val modifiableEyeIsOpen: MutableLiveData<Boolean> = MutableLiveData()
    val eyeIsCount: LiveData<Boolean> = modifiableEyeIsOpen

    fun changeEyeState() {
        modifiableEyeIsOpen.value = !(eyeIsCount.value ?: true)
    }
}
