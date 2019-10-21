package jp.hacks.smartbread.ui.main.wake

import android.content.Context
import android.media.MediaPlayer
import jp.hacks.smartbread.R

internal class BurnBreadBGMServiceImpl(
    private val context: Context
) : BurnBreadBGMService {
    override suspend fun startBGM() {
        val mediaPlayer = MediaPlayer.create(context, R.raw.cat)
        mediaPlayer.start()
    }
}