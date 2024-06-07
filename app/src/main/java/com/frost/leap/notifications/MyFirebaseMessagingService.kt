package com.frost.leap.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.frost.leap.R
import com.frost.leap.utils.Constants
import com.frost.leap.utils.Logger
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * Created by Chenna Rao on 19/05/2023.
 * <p>
 * Frost Interactive
 */
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage.data.isNotEmpty()) {
            Logger.d("Message data payload: ", remoteMessage.data.toString())

            val notification = remoteMessage.notification
            notification?.let {
                // Process or display the notification as needed
                showNotification(applicationContext, it.title, it.body)
            }

        }
    }

    private fun showNotification(context: Context, title: String?, message: String?) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "IDEEP_LEARN_NOTIFY_CHANNEL",
                Constants.APP_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(context, "IDEEP_LEARN_NOTIFY_CHANNEL")
            .setTicker(title).setWhen(0)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentText(message)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
        // Customize other notification properties as needed

        //The notificationManager.notify(0, notificationBuilder.build()) line is responsible for displaying the notification.
        // The first parameter (0 in this example) is the notification ID,
        // which can be used to update or cancel the notification later if needed.
        notificationManager.notify(0, notificationBuilder.build())
    }

    override fun onNewToken(token: String) {
        Logger.d("FCM Token", token)
        super.onNewToken(token)
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String) {
        // Implement your logic to send the token to your server
        // or perform any other operations
    }

}