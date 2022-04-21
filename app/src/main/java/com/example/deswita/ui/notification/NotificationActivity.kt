package com.example.deswita.ui.notification

import android.app.LoaderManager
import android.content.Loader
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deswita.R
import com.example.deswita.databinding.ActivityNotificationBinding
import com.example.deswita.models.Notification
import com.example.deswita.service.NotificationLoader

class NotificationActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<List<Notification>> {

    private lateinit var binding: ActivityNotificationBinding
    private lateinit var adapter: NotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NotificationAdapter(this)

        loaderManager.initLoader(10, Bundle.EMPTY, this)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

    }

    fun initRecyclerviewNotification(notifications : List<Notification>) {
        binding.rvNotification.layoutManager = LinearLayoutManager(this)
        binding.rvNotification.setHasFixedSize(true)
        binding.rvNotification.adapter = adapter
        adapter.setData(notifications)
    }

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<List<Notification>> {
        return NotificationLoader(this)
    }

    override fun onLoadFinished(p0: Loader<List<Notification>>, p1: List<Notification>?) {
        if (p1 != null) {
            initRecyclerviewNotification(p1)
        }
        Log.i("ONLOADFINISHED", "JALAN")
    }
    override fun onLoaderReset(p0: Loader<List<Notification>>?) {
    }
}