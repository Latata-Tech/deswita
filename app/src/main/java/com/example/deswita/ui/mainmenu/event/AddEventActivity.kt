package com.example.deswita.ui.mainmenu.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.deswita.R
import com.example.deswita.databinding.ActivityAddEventBinding

class AddEventActivity : AppCompatActivity() {

    private var _binding: ActivityAddEventBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)
        _binding = ActivityAddEventBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnBack?.setOnClickListener {
            onBackPressed()
        }

        binding?.btnAddEvent?.setOnClickListener {
            Toast.makeText(this, "save event!", Toast.LENGTH_SHORT).show()
        }

    }
}