package com.example.deswita.ui.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deswita.R
import com.example.deswita.databinding.ActivityNotificationBinding
import com.example.deswita.models.Notification

class NotificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationBinding
    private lateinit var adapter: NotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NotificationAdapter(this)

        initRecyclerviewNotification()

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

    }

    fun initRecyclerviewNotification() {
        binding.rvNotification.layoutManager = LinearLayoutManager(this)
        binding.rvNotification.setHasFixedSize(true)
        binding.rvNotification.adapter = adapter


        val notifications = listOf(
            Notification("your password has been successfully chnaged","post_1","des 15, 2021 at 12:20 am"),
            Notification("Saya pernah bekerja di mall, tenant sebelah adalah breadtalk. ","post_2","des 15, 2021 at 12:20 am"),
            Notification("saya pernah melihat mereka siap2 untuk membereskan dagangannya.","user_1","des 15, 2021 at 12:20 am"),
            Notification("Ini foto standing POP yang memuat informasi roti fresh breadtalk yang saya kunjungi tadi sore","post_2","des 15, 2021 at 12:20 am"),
            Notification("Apa yang membuatmu menangis hari ini?","post_1","des 15, 2021 at 12:20 am"),
            Notification("Apa balas dendam terbaik yang pernah Anda lakukan untuk orang yang pernah merendahkan Anda?","user_2","des 15, 2021 at 12:20 am"),
            Notification("Kejadian apa yang membuatmu kesal pada tetanggamu?","post_2","des 15, 2021 at 12:20 am"),
            Notification("Bagaimana cara menemukan lokasi dari sebuah gambar di situs web?","post_1","des 15, 2021 at 12:20 am"),
            Notification("Apa hal yang hanya ada di Twitter dan tidak akan ditemui pada sosial media lain?","post_2","des 15, 2021 at 12:20 am"),
        )

        adapter.setData(notifications)
    }
}