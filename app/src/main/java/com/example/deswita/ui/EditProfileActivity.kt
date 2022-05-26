package com.example.deswita.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.deswita.R
import com.example.deswita.databinding.ActivityEditProfileBinding
import com.example.deswita.constant.EXTRA_NAME
import com.example.deswita.constant.EXTRA_EMAIL

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvName.text = intent.getStringExtra(EXTRA_NAME)
        binding.tvEmail.text = intent.getStringExtra(EXTRA_EMAIL)
    }
}