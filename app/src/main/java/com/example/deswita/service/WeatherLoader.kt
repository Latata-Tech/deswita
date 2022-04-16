package com.example.deswita.service

import android.app.job.JobParameters
import android.content.AsyncTaskLoader
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.deswita.models.weatherResponse
import com.example.deswita.utils.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherLoader(context: Context?) : AsyncTaskLoader<String>(context!!) {

    var weather: weatherResponse? = null

    override fun loadInBackground(): String? {
        return "ok"
    }

    override fun deliverResult(data: String?) {
        if (isStarted) {
            // Deliver result if loader is currently started
            super.deliverResult(data)
        }
    }

    override fun onStartLoading() {
        // Start loading
        forceLoad()
    }

    override fun onStopLoading() {
        cancelLoad()
    }

    override fun onReset() {
        super.onReset()

        // Ensure the loader is stopped
        onStopLoading()
    }
}