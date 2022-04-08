package com.example.deswita.service

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log
import com.example.deswita.models.weatherResponse
import com.example.deswita.utils.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherService: JobService() {

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.e("JOB","START JOB")
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

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.e("JOB","STOP JOB")
        return true
    }
}