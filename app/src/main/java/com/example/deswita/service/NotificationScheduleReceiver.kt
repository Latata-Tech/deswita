package com.example.deswita.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.deswita.R
import com.example.deswita.ui.MainActivity
import java.util.*

class NotificationScheduleReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        var today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        if(today == Calendar.SATURDAY){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notify_id = 301
                val channel_id = "channel_notification_1"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel(channel_id, "schedule_notification", importance)
                val notifyIntent = Intent(context, MainActivity::class.java)
                val notifyPendingIntent = PendingIntent.getActivity(context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
                val notificationBuilder = NotificationCompat.Builder(context!!, channel_id)
                    .setSmallIcon(R.drawable.ic_deswita_icon)
                    .setContentText("Mau kemana liburan minggu ini?")
                    .setContentTitle("Yay!! hari libur tiba")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(notifyPendingIntent)
                val notificationManager : NotificationManager  = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                for(s in notificationManager.notificationChannels){
                    notificationManager.deleteNotificationChannel(s.id)
                }
                notificationManager.createNotificationChannel(channel)
                notificationManager.notify(notify_id, notificationBuilder.build())
            }
        }
    }
}