package com.example.deswita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun registerActivity(view : View)
    {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}