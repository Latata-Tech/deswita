package com.example.deswita.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationScheduleReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val service = Intent(context, NotificationScheduleService::class.java)
        service.putExtra("reason", intent.getStringExtra("reason"))
        service.putExtra("timestamp", intent.getLongExtra("timestamp", 0))
        context.startService(service)
    }
}