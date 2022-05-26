package com.example.deswita.ui.setting

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.deswita.R
import com.example.deswita.databinding.ActivitySettingBinding
import com.example.deswita.ui.EditProfileActivity
import com.example.deswita.constant.EXTRA_NAME
import com.example.deswita.constant.EXTRA_EMAIL

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var sharedPref = this.getSharedPreferences("nama", Context.MODE_PRIVATE )
        var editor = sharedPref?.edit()
        val nameUser = sharedPref?.getString("nama", "")
        val emailUser = nameUser+"@gmail.com"
        binding.tvName.text = nameUser

        binding.tvEmail.text = emailUser


        editor?.commit()

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.clEditProfile.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            intent.putExtra(EXTRA_NAME, nameUser)
            intent.putExtra(EXTRA_EMAIL, emailUser)
            startActivity(intent)
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