package jp.hacks.smartbread.notification

import android.content.Intent

internal interface SendNotificationUsecase {
    suspend fun sendNotification(
        notificationKind: NotificationKind = NotificationKind.simple,
        title: String = "",
        contentText: String = "",
        notificationId: String = "molle",
        notificationChannelGroupId: String = "molle",
        notificationChannelGroupName: String = "molle",
        intent: Intent? = null
    )
}