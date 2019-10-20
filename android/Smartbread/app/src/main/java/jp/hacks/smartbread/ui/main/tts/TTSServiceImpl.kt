package jp.hacks.smartbread.ui.main.tts

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

class TTSServiceImpl(
    private val context: Context
) : TTSService {

    private lateinit var tts: TextToSpeech

    override fun speach(text: String) {
        tts = TextToSpeech((context)) {
            tts.speak(text, TextToSpeech.QUEUE_ADD, null, null)
        }.apply {
            this.language = Locale.JAPAN
        }
    }
}