package com.odroid.movieready.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.odroid.movieready.R
import com.odroid.movieready.view.activity.MainActivity


class NotificationPublisher: BroadcastReceiver() {

    override fun onReceive(context: Context?, p1: Intent?) {
        p1?.let {
            handleAlarmData(context, it)
        }
    }

    private fun handleAlarmData(context: Context?, intent: Intent) {

        context?.let {
            createNotificationChannel(context = it)

            createNotification(
                context = it,
                title = "Let's chill",
                description = "take a break and play some dumb charades"
            )

        }

    }

    private fun createNotification(
        context: Context,
        title : String,
        description : String
    ) {
        // Create an explicit intent for an Activity in your app
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val importance = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            NotificationManager.IMPORTANCE_MAX
        } else {
            NotificationCompat.PRIORITY_MAX
        }
        val builder = NotificationCompat.Builder(context, "CHANNEL_ID")
            .setSmallIcon(R.mipmap.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(description)
            .setPriority(importance)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(System.currentTimeMillis().toInt(), builder.build())
        }
    }

    private fun createNotificationChannel(context: Context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("CHANNEL_ID", "CHANNEL_NAME", importance).apply {
                description = "CHANNEL_DESP"
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }
}
