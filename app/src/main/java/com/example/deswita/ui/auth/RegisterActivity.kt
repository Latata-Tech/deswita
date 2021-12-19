package com.example.deswita.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.deswita.R
import com.example.deswita.databinding.ActivityRegisterBinding
import com.example.deswita.ui.MainActivity

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener(this)
    }

    fun loginActivity(view : View)
    {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            binding.btnRegister.id -> startActivity(Intent(this@RegisterActivity,MainActivity::class.java))
        }
    }
}