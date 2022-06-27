package com.example.deswita.ui.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.deswita.R
import com.example.deswita.databinding.ActivityLoginBinding
import com.example.deswita.ui.MainActivity
import com.example.deswita.ui.MainViewModel
import com.example.deswita.utils.DatabaseController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var mainViewModel: MainViewModel
    lateinit var controller: DatabaseController
    lateinit var editor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        controller = DatabaseController(this)

        val  sharedPref: SharedPreferences = this.getSharedPreferences("login", Context.MODE_PRIVATE)
        editor = sharedPref.edit()

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        window.setFlags(
            FLAG_LAYOUT_NO_LIMITS,
            FLAG_LAYOUT_NO_LIMITS
        )

        binding.btnLogin.setOnClickListener(this)
        binding.tvForgotPassword.setOnClickListener(this)
        binding.textRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            binding.btnLogin.id -> {
                val usernameData = binding.username.text?.trim().toString()
                val passwordData = binding.password.text?.trim().toString()

                when {
                    usernameData.isEmpty() -> {
                        binding.username.error = "Field required"
                    }
                    passwordData.length < 5 ->{
                        binding.password.error = "Password must be longer than 5"
                    }
                   else -> {
                       GlobalScope.launch(Dispatchers.IO) {
                           val getUser =
                               withContext(Dispatchers.Default) {
                                   controller.getUser(
                                       usernameData,
                                       passwordData
                                   )
                               }
                           if(getUser){
                               if(binding.cbRememberMe.isChecked){
                                   editor.putString("username", usernameData)
                                   editor.putString("password", passwordData)
                               }
                               val intent = Intent(this@LoginActivity, MainActivity::class.java)
                               startActivity(intent)
                           }
                       }
                    }
                }
            }

            binding.tvForgotPassword.id -> {
                val changePasswordFragment = ChangePasswordFragment()
                changePasswordFragment.show(supportFragmentManager,ChangePasswordFragment::class.java.simpleName)
            }
        }
    }

}