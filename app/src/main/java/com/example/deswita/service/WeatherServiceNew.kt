package com.example.deswita.service

import android.app.Service
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.deswita.models.weatherResponse
import com.example.deswita.utils.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherServiceNew : JobService() {
    val TAG = WeatherServiceNew::class.java
    override fun onStartJob(p0: JobParameters?): Boolean {
        Log.i(TAG.toString(), "JOB JALAN")
        getWeather(p0)
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        return true
    }

    private fun getWeather(param: JobParameters?) {
        val client = ApiConfig.getApiService().getWeather()
        client.enqueue(object : Callback<weatherResponse> {
            override fun onResponse(
                call: Call<weatherResponse>,
                response: Response<weatherResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        Log.w("GET WEATER",responseBody.toString())
                        jobFinished(param,false)
                    }
                } else {
                    Log.e("GET WEATHER", "onFailure: ${response.message()}")
                    jobFinished(param,true)
                }
            }
            override fun onFailure(call: Call<weatherResponse>, t: Throwable) {
                Log.e("GET WEATHER", "onFailure")
                jobFinished(param,true)
            }
        })
    }
}