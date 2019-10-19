package jp.hacks.smartbread.ui.aiface

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

internal class FaceViewModel : ViewModel() {
    private val modifiableEyeIsOpen: MutableLiveData<Boolean> = MutableLiveData()
    val eyeIsCount: LiveData<Boolean> = modifiableEyeIsOpen

    var newsLiveData: WeatherNerwsLiveData = WeatherNerwsLiveData(viewModelScope)

    init {
        newsLiveData = WeatherNerwsLiveData(viewModelScope)
    }

    fun changeEyeState() {
        modifiableEyeIsOpen.value = !(eyeIsCount.value ?: true)
    }

    fun loadNews() {
        newsLiveData.loadNews()
    }
}
