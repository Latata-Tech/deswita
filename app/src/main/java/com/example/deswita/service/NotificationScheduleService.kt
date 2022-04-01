package com.example.deswita.service

import android.app.*
import android.content.Intent
import android.content.Context
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import com.example.deswita.R
import com.example.deswita.ui.MainActivity
import java.util.*

const val CHANNEL_ID = "notification_service";
const val CHANNEL_NAME = "Sample Notification"
class NotificationScheduleService : IntentService("NotificationScheduleService") {
    private lateinit var notification : Notification
    private val notificationId : Int = 1000

    override fun onHandleIntent(intent: Intent?) {
        createChannel()

        var timeStamp : Long = 0
        if(intent != null && intent.extras != null){
            timeStamp = intent.extras!!.getLong("timestamp")
        }

        if(timeStamp > 0){
            val context = this.applicationContext
            var notificationManager : NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notifyIntent = Intent(this, MainActivity::class.java)
            val title = "Hari libur tiba"
            val message = "Ayo lihat tempat pariwisata yang dapat dikunjungi minggu ini"

            notifyIntent.putExtra("title", title)
            notifyIntent.putExtra("message", message)
            notifyIntent.putExtra("notification", true)

            notifyIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timeStamp

            val pendingIntent = PendingIntent.getActivity(context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            val res = this.resources
            val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


                notification = Notification.Builder(this, CHANNEL_ID)
                    // Set the intent that will fire when the user taps the notification
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.ic_deswita_icon)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setStyle(Notification.BigTextStyle()
                        .bigText(message))
                    .setContentText(message).build()
            } else {

                notification = Notification.Builder(this)
                    // Set the intent that will fire when the user taps the notification
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.ic_deswita_icon)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.ic_launcher))
                    .setAutoCancel(true)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setContentTitle(title)
                    .setStyle(Notification.BigTextStyle()
                        .bigText(message))
                    .setSound(uri)
                    .setContentText(message).build()
            }

            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(notificationId, notification)
        }
    }

    private fun createChannel()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val context = this.applicationContext
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
            notificationChannel.description = "Hello"
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}