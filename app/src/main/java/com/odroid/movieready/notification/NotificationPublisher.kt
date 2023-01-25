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
import com.odroid.movieready.entity.Notification
import com.odroid.movieready.view.activity.HomeActivity

class NotificationPublisher : BroadcastReceiver() {

    private val channelId = "110041"
    private val channelName = "play"

    override fun onReceive(context: Context?, p1: Intent?) {
        p1?.let {
            handleAlarmData(context, it)
        }
    }

    private fun handleAlarmData(context: Context?, intent: Intent) {
        context?.let {
            createNotificationChannel(context = it)
            createNotification(
                context = it
            )
        }
    }

    private fun createNotification(
        context: Context,
    ) {
        val intent = Intent(context, HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val importance = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            NotificationManager.IMPORTANCE_HIGH
        } else {
            NotificationCompat.PRIORITY_MAX
        }
        val notificationContent = getRandomNotificationContent()
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_next)
            .setContentTitle(notificationContent.title)
            .setContentText(notificationContent.description)
            .setPriority(importance)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            notify(System.currentTimeMillis().toInt(), builder.build())
        }
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = "Remind to play Dumb Charades"
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun getRandomNotificationContent(): Notification {
        val notifications = listOf(
            Notification("Take a chill pill", "Why not take some time out and play dumb charades?"),
            Notification("Mere paas maa hai", "Deewar (1975)"),
            Notification("Lighten up your mood", "Let's guess some movies"),
            Notification("Did you forget to take a break?", "Come on! Let's play!"),
            Notification("Main aaj bhi phenke hue paise nahi uthata", "Agneepath (1990)"),
            Notification("Jab tak hai jaan, jab tak hai jaan", "Jab Tak Hai Jaan (2012)"),
            Notification("Kuch kuch hota hai", "Kuch Kuch Hota Hai (1998)"),
            Notification("Mogambo khush hua", "Mr India (1987)"),
            Notification("Kitne admi the", "Sholay (1975)"),
            Notification("Picture abhi baaki hai mere dost", "Om Shanti Om (2007)"),
            Notification("Ja Simran ja jee le apni zindagi", "Dilwale Dulhaniya Le Jayenge (1995)"),
            Notification("Basanti in kutto ke samne mat naachna", "Sholay (1975)"),
            Notification("Pushpa I hate tears", "Amar Prem (1972)"),
            Notification("Watan ke aage kuch nahi, khud bhi nahi", "Raazi (2018)"),
        )
        return notifications.random()
    }
}
