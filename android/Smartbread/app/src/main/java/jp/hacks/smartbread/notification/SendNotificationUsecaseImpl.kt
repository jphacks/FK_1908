package jp.hacks.smartbread.notification

import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import jp.hacks.smartbread.R

internal class SendNotificationUsecaseImpl(
    private val context: Context
) : SendNotificationUsecase {

    override suspend fun sendNotification(
        notificationKind: NotificationKind,
        title: String,
        contentText: String,
        notificationId: String,
        notificationChannelGroupId: String,
        notificationChannelGroupName: String,
        intent: Intent?
    ) {

        val notification = NotificationCompat
            .Builder(context, notificationId)
            .setContentText(contentText)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()

        val notificationManager =
            context.getSystemService(NotificationManager::class.java) ?: return

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val notificationGroup = NotificationChannelGroup(
                notificationChannelGroupId,
                notificationChannelGroupName
            )
            notificationManager.createNotificationChannelGroup(notificationGroup)

            val notificationChannel =
                notificationManager.getNotificationChannel(notificationId)
                    ?: NotificationChannel(
                        notificationId,
                        "molle",
                        NotificationManager.IMPORTANCE_DEFAULT
                    ).apply {
                        description = "詳細"
                    }
            notificationManager.createNotificationChannel(notificationChannel)
        }

        notificationManager.notify(SystemClock.uptimeMillis().toInt(), notification)
    }
}