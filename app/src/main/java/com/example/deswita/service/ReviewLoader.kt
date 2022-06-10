package com.example.deswita.service

import android.content.AsyncTaskLoader
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.deswita.models.Review
import com.example.deswita.utils.UserReviewHelperDB
import kotlin.random.Random

class ReviewLoader(context: Context?, private val destination_id: Int) : AsyncTaskLoader<String>(context!!) {
    private var deswitaDB : UserReviewHelperDB? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun loadInBackground(): String {
        deswitaDB = UserReviewHelperDB(context)

        val result = deswitaDB?.viewAllData(destination_id)
        if(result.isNullOrEmpty()) {
            val preReviewData = cretateDataReview(destination_id)
            for(data in preReviewData) {
                deswitaDB?.addUserReview(data)
            }
        }
        Thread.sleep(1000L)
        return "Success"
    }

    private fun cretateDataReview(destination_id: Int) : List<Review> {
        val data  = ArrayList<Review>()
        for (i in 1..5) {
            data.add(Review(
                i,
                "John Doe",
                "",
                "2022-06-08",
                "Tempat nya keren",
                Random.nextDouble(1.0, 5.0).toFloat(),
                destination_id,
                1
            ))
        }
        return data
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