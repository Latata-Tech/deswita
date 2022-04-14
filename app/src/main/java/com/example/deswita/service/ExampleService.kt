package com.example.deswita.service

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

class ExampleService: JobService() {

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.e("JOB 1","START")
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.e("JOB 1","STOP")
        return true
    }
}