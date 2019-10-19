package jp.hacks.smartbread.ui.main

import android.app.Application
import android.speech.tts.TextToSpeech
import androidx.lifecycle.AndroidViewModel

internal class TTSViewModel(
    application: Application
) : AndroidViewModel(application) {

    private var textToSpeech: TextToSpeech
    private var isEnabled = true

    init {
        textToSpeech = TextToSpeech(application.applicationContext) {
            isEnabled = it == 0
        }
    }

    fun speech(text: String) {
        if (!isEnabled) {
            return
        }

        textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null, null)
    }
}