package com.example.deswita.ui.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.deswita.R
import com.example.deswita.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.clEditProfile.setOnClickListener {
            Toast.makeText(this, "edit profile on progress", Toast.LENGTH_SHORT).show()
        }

        binding.clChangePassword.setOnClickListener {
            Toast.makeText(this, "change password on progress", Toast.LENGTH_SHORT).show()
        }
        binding.clFaq.setOnClickListener {
            Toast.makeText(this, "faq on progress", Toast.LENGTH_SHORT).show()
        }
        binding.clsSetting.setOnClickListener {
            Toast.makeText(this, "setting on progress", Toast.LENGTH_SHORT).show()
        }

    }
}