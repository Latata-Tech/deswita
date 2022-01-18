package com.example.deswita.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager.LayoutParams.*
import com.example.deswita.R
import com.example.deswita.databinding.ActivityLoginBinding
import com.example.deswita.databinding.ActivityRegisterBinding
import com.example.deswita.ui.MainActivity

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            FLAG_LAYOUT_NO_LIMITS,
            FLAG_LAYOUT_NO_LIMITS
        )

        binding.btnLogin.setOnClickListener(this)
        binding.tvForgotPassword.setOnClickListener(this)
    }

    fun registerActivity(view : View)
    {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            binding.btnLogin.id -> startActivity(Intent(this@LoginActivity,MainActivity::class.java))
            binding.tvForgotPassword.id -> {
                val changePasswordFragment = ChangePasswordFragment()
                changePasswordFragment.show(supportFragmentManager,ChangePasswordFragment::class.java.simpleName)
            }
        }
    }

}