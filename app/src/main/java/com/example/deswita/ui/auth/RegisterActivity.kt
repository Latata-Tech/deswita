package com.example.deswita.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
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
        finish()
    }




    override fun onClick(v: View?) {

        val fullNameReg = binding.name.text?.trim().toString()
        val usernameReg = binding.username.text?.trim().toString()
        val passwordReg = binding.password.text?.trim().toString()

        when(v?.id) {

            binding.btnRegister.id -> {


                when {
                    fullNameReg.isEmpty()->{
                        binding.name.error = "Field required"
                    }
                    usernameReg.isEmpty() -> {
                        binding.username.error = "Field required"
                    }
                    passwordReg.length<5 -> {
                        binding.password.error = "Password must be longer than 5"
                    }
                    fullNameReg.isNotBlank() && usernameReg.isNotBlank() && passwordReg.isNotBlank()->{
                        startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))
                        Toast.makeText(this, "Registrasi berhasil", Toast.LENGTH_SHORT).show()
                    }

                }



            }

        }

        Intent(this, LoginActivity::class.java).also {
            it.putExtra("EXTRA_NAME", fullNameReg)
            it.putExtra("EXTRA_USER", usernameReg)
            it.putExtra("EXTRA_PASSWORD", passwordReg)
            startActivity(it)
        }



    }




//    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
//        super.onSaveInstanceState(outState)
//
//        val data = getDataRegis()
//
//        if(data != null){
//            outState.putString("data",data.toString())
//        }
//
//    }

//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        val data = savedInstanceState.gets
//    }


}


