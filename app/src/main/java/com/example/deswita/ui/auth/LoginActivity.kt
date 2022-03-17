package com.example.deswita.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager.LayoutParams.*
import androidx.lifecycle.ViewModelProvider
import com.example.deswita.R
import com.example.deswita.databinding.ActivityLoginBinding
import com.example.deswita.databinding.ActivityRegisterBinding
import com.example.deswita.ui.EXTRA_USER
import com.example.deswita.ui.MainActivity
import com.example.deswita.ui.MainViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

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
            binding.btnLogin.id -> {
//                val usernameData = binding.username.text?.trim().toString()
//                val passwordData = binding.password.text?.trim().toString()
//                var usernameLogin = intent.getStringExtra("EXTRA_USER")
//                var passwordUser = intent.getStringExtra("EXTRA_PASSWORD")
//
//                when {
//                    usernameData.isEmpty() -> {
//                        binding.username.error = "Field required"
//                    }
//                    passwordData.length<5 ->{
//                        binding.password.error = "Password must be longer than 5"
//                    }
//                    passwordData != passwordUser || usernameData != usernameLogin  ->{
//                        binding.password.error = "Username or Password not valid"
//                    }
//                   passwordData == passwordUser || usernameData == usernameLogin ->{
//                       var intent = Intent(this,MainActivity::class.java)
//                       intent.putExtra(EXTRA_USER, usernameLogin)
//                       startActivity(intent)
//                    }
//                }

                var intent = Intent(this,MainActivity::class.java)
                startActivity(intent)

            }
            binding.tvForgotPassword.id -> {
                val changePasswordFragment = ChangePasswordFragment()
                changePasswordFragment.show(supportFragmentManager,ChangePasswordFragment::class.java.simpleName)
            }
        }
    }

}