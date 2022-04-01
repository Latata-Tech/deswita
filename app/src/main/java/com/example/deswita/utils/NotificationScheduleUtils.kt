package com.example.deswita.utils

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import com.example.deswita.service.NotificationScheduleReceiver
import java.util.*

class NotificationScheduleUtils {
    fun setNotification(timeInMilliSeconds: Long, activity: Activity) {
        if (timeInMilliSeconds > 0) {
            val alarmManager = activity.getSystemService(Activity.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(activity.applicationContext, NotificationScheduleReceiver::class.java) // AlarmReceiver1 = broadcast receiver
            alarmIntent.putExtra("reason", "notification")
            alarmIntent.putExtra("timestamp", timeInMilliSeconds)
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timeInMilliSeconds
            val pendingIntent = PendingIntent.getBroadcast(activity, 0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT)
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

        }
    }
}