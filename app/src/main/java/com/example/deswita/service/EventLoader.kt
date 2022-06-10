package com.example.deswita.service

import android.content.AsyncTaskLoader
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.deswita.models.Event
import com.example.deswita.models.Review
import com.example.deswita.utils.EventHelperDB
import com.example.deswita.utils.UserReviewHelperDB
import kotlin.random.Random

class EventLoader(context: Context?) : AsyncTaskLoader<String>(context!!) {
    private var deswitaDB : EventHelperDB? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun loadInBackground(): String {
        deswitaDB = EventHelperDB(context)

        val preReviewData = createEvents()
        for(data in preReviewData) {
            deswitaDB?.addEvent(data)
        }
        Thread.sleep(1000L)
        return "Success"
    }

    private fun createEvents() : List<Event> {
        val data  = ArrayList<Event>()
        for (i in 1..5) {
            data.add(Event(
                i,
                "John Doe ${i}",
                "1${i} juni 2022",
                "bali resort ${i}",
                "Bali ,Indonesia",
                "A magical blend of culture, people, nature "
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