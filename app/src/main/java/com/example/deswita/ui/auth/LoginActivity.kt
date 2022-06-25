package com.example.deswita.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager.LayoutParams.*
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.deswita.R
import com.example.deswita.databinding.ActivityLoginBinding
import com.example.deswita.databinding.ActivityRegisterBinding
import com.example.deswita.ui.EXTRA_USER
import com.example.deswita.ui.MainActivity
import com.example.deswita.ui.MainViewModel
import com.example.deswita.utils.DatabaseController
import kotlinx.coroutines.*
import java.util.*
import kotlin.concurrent.schedule


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var mainViewModel: MainViewModel
    lateinit var controller: DatabaseController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        controller = DatabaseController(this)

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
                           val getUser = async { loadUser(usernameData, passwordData) }.await()
                           if(getUser){
                               Log.i("Ingfo kesini maseh", "asdoaksodkasokdoaskdoaskd")
                               val intent = Intent(this@LoginActivity, MainActivity::class.java)
                               startActivity(intent)
                           }
                           Log.i("Pertama kali mulai maseh", "kansdiajsodkoasjdiajsidjasijdas")
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

    private suspend fun loadUser(username: String, password: String): Boolean {
        val user = controller.getUser(username, password)
        delay(700L)
        return user
    }

}