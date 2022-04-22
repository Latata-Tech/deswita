package com.example.deswita.service

import android.content.AsyncTaskLoader
import android.content.Context
import com.example.deswita.models.Notification

class NotificationLoader(context: Context?) : AsyncTaskLoader<List<Notification>>(context!!) {
    override fun loadInBackground(): List<Notification>? {
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

        Thread.sleep(2000L)

        return notifications
    }

    override fun deliverResult(data: List<Notification>?) {
        if (isStarted) {
            // Deliver result if loader is currently started
            super.deliverResult(data)
        }
    }

    override fun onStartLoading() {
        // Start loading
        forceLoad()
    }

    override fun onStopLoading() {
        cancelLoad()
    }

    override fun onReset() {
        super.onReset()

        // Ensure the loader is stopped
        onStopLoading()
    }
}