package com.example.deswita.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import androidx.core.app.JobIntentService
import com.example.deswita.constant.CURRENT_TIMER_RUNNING
import com.example.deswita.constant.JOB_ID_TIMER
import com.example.deswita.constant.TIMER_RUNNING_FINISH
import com.example.deswita.constant.TIMER_RUNNING_PROGRESS
import java.util.*

class TimerService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        var timer = 60
        do {
            timer -= 1
            val intentTimer = Intent(CURRENT_TIMER_RUNNING)
            intentTimer.putExtra(TIMER_RUNNING_PROGRESS, timer)
            intentTimer.putExtra(TIMER_RUNNING_FINISH, false)

            if(timer <= 0)
                intentTimer.putExtra(TIMER_RUNNING_FINISH, true)

            sendBroadcast(intentTimer)
        }while (timer > 0)
    }

    companion object {
        fun enqueueWork(context: Context, intent: Intent){
            enqueueWork(context, TimerService::class.java, JOB_ID_TIMER, intent)
        }
    }
}