package jp.hacks.smartbread.ui.main.tts

internal interface TTSService {
    suspend fun speach(text: String)
}