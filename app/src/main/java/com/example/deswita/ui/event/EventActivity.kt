package com.example.deswita.ui.event

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.example.deswita.R
import com.example.deswita.databinding.ActivityEventBinding
import com.example.deswita.models.Event
import com.example.deswita.utils.Utils
import com.example.deswita.utils.*

class EventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventBinding
    private lateinit var event: Event

    companion object {
        const val EXTRA_EVENT = "extra_event"
        const val EXTRA_SHARE = "extra_share"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        event = intent.getParcelableExtra<Event>(EXTRA_EVENT) as Event

        binding.ivEvent.load(Utils.getImageDrawable(this,event.image))
        binding.tvTitle.text = event.name.CapitalizeAllWord()
        binding.tvLocation.text = event.location.CapitalizeFirstWord()


        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnShare.setOnClickListener {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND

                putExtra(Intent.EXTRA_TEXT,"${event.name.CapitalizeAllWord()} \n ${event.description.CapitalizeFirstWord()} \n\n ${event.location.CapitalizeFirstWord()}")
                type = "text/plain"

                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

            }
            startActivity(Intent.createChooser(shareIntent,"Pilih dong"))
        }
    }
}