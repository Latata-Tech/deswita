package com.example.deswita.ui.destination

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.deswita.R
import com.example.deswita.databinding.ActivityAddDestinationBinding
import com.example.deswita.databinding.ActivityAddEventBinding

class AddDestinationActivity : AppCompatActivity() {

    private var _binding: ActivityAddDestinationBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_destination)
        _binding = ActivityAddDestinationBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnBack?.setOnClickListener {
            onBackPressed()
        }

        binding?.btnAddDestination?.setOnClickListener {
            Toast.makeText(this, "save destination!", Toast.LENGTH_SHORT).show()
        }
    }
}