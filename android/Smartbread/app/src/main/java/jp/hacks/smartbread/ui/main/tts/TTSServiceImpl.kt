package jp.hacks.smartbread.ui.main.tts

import android.content.Context
import android.speech.tts.TextToSpeech

class TTSServiceImpl(
    private val context: Context
) : TTSService {

    private var textToSpeech: TextToSpeech = TextToSpeech((context)) {}

    override suspend fun speach(text: String) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null, null)
    }
}