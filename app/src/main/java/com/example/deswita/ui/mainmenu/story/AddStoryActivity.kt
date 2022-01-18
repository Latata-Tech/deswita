package com.example.deswita.ui.mainmenu.story

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.deswita.R
import com.example.deswita.databinding.ActivityAddStoryBinding

class AddStoryActivity : AppCompatActivity() {

    private var _binding: ActivityAddStoryBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_story)
        _binding = ActivityAddStoryBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding?.btnBack?.setOnClickListener {
            onBackPressed()
        }

        binding?.btnAddStory?.setOnClickListener {
            Toast.makeText(this, "Add story!", Toast.LENGTH_SHORT).show()
        }

    }
}