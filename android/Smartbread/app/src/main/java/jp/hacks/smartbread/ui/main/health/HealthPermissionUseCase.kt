package jp.hacks.smartbread.ui.main.health

import androidx.appcompat.app.AppCompatActivity

internal interface HealthPermissionUseCase {
    fun requestPermission(activity: AppCompatActivity)
}