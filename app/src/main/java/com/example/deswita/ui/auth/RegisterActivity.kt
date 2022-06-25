package com.example.deswita.ui.auth

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsManager
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.deswita.R
import com.example.deswita.databinding.ActivityRegisterBinding
import com.example.deswita.service.OtpReceiver
import com.example.deswita.utils.DatabaseController
import com.example.deswita.utils.PermissionManager


class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegisterBinding

    lateinit var controller: DatabaseController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        controller = DatabaseController(this)

        val  sharedPref = this.getSharedPreferences("nama", Context.MODE_PRIVATE)

        binding.btnRegister.setOnClickListener(this)

        val message = intent.getStringExtra(EXTRA_SMS_MESSAGE)
        binding.name.setText(message)

        var SMSReceiver = OtpReceiver()
        var filter2 = IntentFilter()
        filter2.addAction(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
        registerReceiver(SMSReceiver, filter2)
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
                    fullNameReg.isEmpty() ->{
                        binding.name.error = "Field required"
                    }
                    usernameReg.isEmpty() -> {
                        binding.username.error = "Field required"
                    }
                    passwordReg.isEmpty() || passwordReg.length<5 -> {
                        binding.password.error = "Password must be longer than 5"
                    }
                    fullNameReg.isNotEmpty() || usernameReg.isNotEmpty() || passwordReg.isNotEmpty() ->{
                        controller.saveUser(fullNameReg, usernameReg, passwordReg)
                        Intent(this, LoginActivity::class.java).also {
                            it.putExtra("EXTRA_NAME", fullNameReg)
                            it.putExtra("EXTRA_USER", usernameReg)
                            it.putExtra("EXTRA_PASSWORD", passwordReg)
                            startActivity(it)
                        }
                    }
                }
                val  sharedPref: SharedPreferences = this.getSharedPreferences("nama", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putString("nama",fullNameReg)
                editor.commit()

                PermissionManager.check(this, Manifest.permission.RECEIVE_SMS, SMS_REQUEST_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == SMS_REQUEST_CODE) {
            when {
                grantResults[0] == PackageManager.PERMISSION_GRANTED -> Toast.makeText(this, "Sms receiver permission diterima", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this, "Sms receiver permission ditolak", Toast.LENGTH_SHORT).show()
            }
        }
    }

//    protected fun sendSMSMessage() {
//        phoneNo = "085273580367"
//        message = "harap dirahasiakan OTP kamu dari orang lain \n #123"
//        if (ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED ) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale( this, Manifest.permission.SEND_SMS ) ) {
//            } else {
//                ActivityCompat.requestPermissions( this, arrayOf(Manifest.permission.SEND_SMS), MY_PERMISSIONS_REQUEST_SEND_SMS )
//            }
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        when (requestCode) {
//            MY_PERMISSIONS_REQUEST_SEND_SMS -> {
//                if (grantResults.size > 0
//                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
//                ) {
//                    val smsManager: SmsManager = SmsManager.getDefault()
//                    smsManager.sendTextMessage(phoneNo, "55555", message, null, null)
//                    Toast.makeText(
//                        applicationContext, "SMS sent.",
//                        Toast.LENGTH_LONG
//                    ).show()
//                } else {
//                    Toast.makeText(
//                        applicationContext,
//                        "SMS faild, please try again.", Toast.LENGTH_LONG
//                    ).show()
//                    return
//                }
//            }
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }


    companion object {
        private const val SMS_REQUEST_CODE = 101
        val EXTRA_SMS_NO = "EXTRA_SMS_NO"
        val EXTRA_SMS_MESSAGE = "EXTRA_SMS_MESSAGE"
    }
}


