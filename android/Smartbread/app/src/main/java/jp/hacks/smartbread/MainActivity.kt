package jp.hacks.smartbread

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jp.hacks.smartbread.ui.aiface.FaceFragment
import jp.hacks.smartbread.ui.main.DebugFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            // TODO Build variant とかを使ったものに実装を差し替えたい
            val is_debug_mode = true

            if (is_debug_mode) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, DebugFragment.newInstance())
                    .commitNow()
            } else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, FaceFragment.newInstance())
                    .commitNow()
            }
        }

        GoogleFitApiDebugActivity.startActivity(this)
    }

    fun navigateToFaceFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FaceFragment.newInstance())
            .commitNow()
    }
}
