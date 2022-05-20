package com.example.deswita.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import java.util.*

class NotificationSchedulerService : Service() {
    private var alarmManager : AlarmManager? = null
    private var pendingIntent : PendingIntent? = null
    override fun onBind(intent: Intent): IBinder?= null

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        if(pendingIntent != null){
            alarmManager?.cancel(pendingIntent)
            pendingIntent?.cancel()
        }
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, NotificationScheduleReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, 108, intent, 0)
        alarmManager?.setRepeating(AlarmManager.RTC_WAKEUP, scheduleNotification(), AlarmManager.INTERVAL_DAY, pendingIntent)
        return START_STICKY
    }

    private fun scheduleNotification(): Long {
        var setAlarmTime = Calendar.getInstance()
        setAlarmTime.set(Calendar.SECOND, 5)
        return setAlarmTime.timeInMillis
    }
}