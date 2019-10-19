package jp.hacks.smartbread.ui.main.health

import android.content.Context

internal interface HealthPermissionRepository{
    fun requestPermission(context: Context)
}