package com.example.admin

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admin.databinding.ActivityMainBinding
import com.example.deswita.models.Event

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var eventAdapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        eventAdapter = EventAdapter(this)

        initialRecyclerView()

        var eventDummy4 = arrayListOf<Event>(
            Event(
                id = 15,
                image = "ev_15",
                date= "3 january 2022",
                name = "Taman Labirin",
                location = "Kab. Karo, Sumatera Utara.",
                description = "Jangan lupa berkunjung di taman Labirin saat anda berada di kota Medan."
            ),
            Event(
                id = 16,
                image = "ev_16",
                date= "3 january 2022",
                name = "Funland Mikie Holiday",
                location = "Jl. Jamin Ginting, Sempajaya, Kec. Berastagi",
                description = "namun semua akan sebanding dengan seluruh fasilitas yang disediakan ditempat ini."
            ),
            Event(
                id = 17,
                image = "ev_17",
                date= "3 january 2022",
                name = "Gedung London",
                location = "Jl. Jend. Ahmad Yani No.2",
                description = "Gedung yang terletak di jalan Kesawen kota Medan"
            ),
            Event(
                id = 18,
                image = "ev_18",
                date= "3 january 2022",
                name = "Sipisopiso",
                location = "Tongging, Kec. Merek, Kab. Karo, Sumatera Utara.",
                description = "anda dapat menikmati pemandangan dari danau Toba."
            ),
        )

        eventAdapter.setData(eventDummy4)

    }

    private fun initialRecyclerView() {
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.setHasFixedSize(true)
        binding.rv.recycledViewPool.clear()
        binding.rv.adapter = eventAdapter

        eventAdapter.setOnClickItemCallback(object: EventAdapter.OnClickItemCallback{
            override fun onClick(event: Event) {
                Toast.makeText(this@MainActivity,event.name,Toast.LENGTH_SHORT).show()
            }
        })
    }

}