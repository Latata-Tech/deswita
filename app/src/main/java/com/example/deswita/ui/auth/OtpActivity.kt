package com.example.deswita.ui.auth

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.deswita.R
import com.example.deswita.constant.CURRENT_TIMER_RUNNING
import com.example.deswita.constant.TIMER_RUNNING_FINISH
import com.example.deswita.constant.TIMER_RUNNING_PROGRESS
import com.example.deswita.databinding.ActivityOtpBinding
import com.example.deswita.service.TimerService
import com.example.deswita.ui.MainActivity
import java.util.regex.Matcher
import java.util.regex.Pattern

class OtpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpBinding

    private val verifOtpReceiver = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val isFinish = intent?.getBooleanExtra(TIMER_RUNNING_FINISH,true)

            if(isFinish == true) {

                val changePassword3Fragment = ChangePassword3Fragment()
                changePassword3Fragment.show(supportFragmentManager,ChangePassword3Fragment::class.java.simpleName)

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val otp = intent.getStringExtra(EXTRA_SMS_MESSAGE)!!.toCharArray()
        binding.etOtp1.setText(otp[0].toString())
        binding.etOtp2.setText(otp[1].toString())
        binding.etOtp3.setText(otp[2].toString())
        binding.etOtp4.setText(otp[3].toString())

        val myService = Intent(this,TimerService::class.java)

        binding.btnVerification.setOnClickListener {
            it.visibility = View.GONE
            binding.progress.visibility = View.VISIBLE
            TimerService.enqueueWork(this,myService)
        }

        val filterVerifOtp = IntentFilter(CURRENT_TIMER_RUNNING)
        registerReceiver(verifOtpReceiver,filterVerifOtp)
    }

    companion object {
        val EXTRA_SMS_NO = "EXTRA_SMS_NO"
        val EXTRA_SMS_MESSAGE = "EXTRA_SMS_MESSAGE"
    }

}