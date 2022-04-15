package com.example.deswita.ui.mainmenu.event

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import com.example.deswita.R
import com.example.deswita.databinding.ActivityAddEventBinding
import com.example.deswita.models.Event
import com.example.deswita.ui.MainActivity
import com.example.deswita.ui.event.EventActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.schedule

class AddEventActivity : AppCompatActivity() {

    private var _binding: ActivityAddEventBinding? = null
    private val binding get() = _binding
    private val requestCodeEvent = 200
    private var imageUri: Uri? = null
    private lateinit var date: String

    private val CHANNEL_ID = "channel_id"
    private val notificationId = 111

    companion object {
        const val EXTRA_DATE = "extra_date"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)
        _binding = ActivityAddEventBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        createNotificationChannel()

        binding?.ivAddEvent?.setOnClickListener {
            selectImage()
        }

        binding?.btnBack?.setOnClickListener {
            onBackPressed()
        }

        binding?.btnAddEvent?.setOnClickListener {
            val title = binding?.edtTitleAddEvent?.text.toString()
            val description = binding?.edtDescriptionAddEvent?.text.toString()
            val location = binding?.edtLocationAddEvent?.text.toString()
            val date = date

            when {
                location.isEmpty() -> binding?.edtLocationAddEvent?.error = "required"
                title.isEmpty() -> binding?.edtTitleAddEvent?.error = "required"
                description.isEmpty() -> binding?.edtDescriptionAddEvent?.error = "required"
                imageUri == null -> Toast.makeText(this, "image required", Toast.LENGTH_SHORT)
                    .show()
                else -> {
                    val idEvent = (1..9999).random()
                    val event =
                        Event(idEvent, imageUri.toString(), date, title, location, description)
                    sendNotification(event)
                }
            }
        }

        binding?.btnEventCalendar?.setOnClickListener {
            val calendarFragment = CalendarFragment()

            val bundle = Bundle()
            bundle.putString(EventFragment.EXTRA_DATE, date)
            calendarFragment.arguments = bundle

            calendarFragment.show(supportFragmentManager, CalendarFragment::class.java.simpleName)
            calendarFragment.setOnClickItemCallback(object : CalendarFragment.OnClickItemCallback {
                override fun onClick(dt: Date) {
                    setDate(dt)
                }
            })
        }

        if (savedInstanceState != null) {
            date = savedInstanceState.getString(EXTRA_DATE).toString()
            binding?.tvDateEvent?.text = date
        } else {
            setDate(Date())
        }
    }

    private fun setDate(dt: Date) {
        val sdf = SimpleDateFormat("dd MMMM yyyy")
        val currentDate = sdf.format(dt)
        date = currentDate
        binding?.tvDateEvent?.text = date
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Event"
            val descriptionText = "New event appeared"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(event: Event) {

        val intent = Intent(this, EventActivity::class.java)
        intent.putExtra(EventActivity.EXTRA_EVENT, event)

        val pendingIntent = TaskStackBuilder.create(this)
            .addParentStack(EventActivity::class.java)
            .addNextIntent(intent)
            .getPendingIntent(110, PendingIntent.FLAG_UPDATE_CURRENT)

        val bitmapLargeIcon =
            BitmapFactory.decodeResource(applicationContext.resources, R.drawable.ic_deswita_icon)
        val bitmapImage = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_deswita_icon)
            .setContentTitle(event.name)
            .setContentText(event.description)
            .setLargeIcon(bitmapLargeIcon)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmapImage))
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }
    }

    fun selectImage() {
        var intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(intent, requestCodeEvent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == requestCodeEvent) {
            Thread(Runnable {
                imageUri = data?.data
                Timer().schedule(900) {
                    binding?.ivAddEvent?.post {
                        binding?.ivAddEvent?.setImageURI(imageUri)
                    }
                }
            }).start()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(EXTRA_DATE, date)
        super.onSaveInstanceState(outState)
    }
}