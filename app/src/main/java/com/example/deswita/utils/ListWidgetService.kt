package com.example.deswita.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.example.deswita.R
import com.example.deswita.models.Event

class ListWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return ListRemoteViewFactory(this.applicationContext, intent)
    }
}

class ListRemoteViewFactory(private val context: Context, intent: Intent) : RemoteViewsService.RemoteViewsFactory {
    private var widgetItems : List<Event> = listOf(
        Event(
            id = 6,
            image = "ev_6",
            date = "1 january 2022",
            name = "Istana Maimun",
            location = "Jl. Sultan Ma’moen Al Rasyid",
            description = "Istana Maimun merupakan salah satu objek wisata ikonik di kota Medan."
        ),
        Event(
            id = 7,
            image = "ev_7",
            date = "1 january 2022",
            name = "Danau Siombak",
            location = "Jl. Pasar Nippon Ujung Kel, Paya Pasir,",
            description = "Perlu diketahui bahwa danau Siombak ini merupakan salah satu danau buatan yang terletak diantara sungai Deli"
        ),
        Event(
            id = 8,
            image = "ev_8",
            date = "1 january 2022",
            name = "Maha Vihara Adhi Maitreya",
            location = "Jl. Cemara Asri Boulevard Raya No.Utara, Medan Estate",
            description = "kota Medan juga memiliki wisata religi yang dapat anda kunjungi. "
        )
    )
    override fun onCreate() {
    }

    override fun onDataSetChanged() {
    }

    override fun onDestroy() {
    }

    override fun getCount(): Int {
        return widgetItems.size
    }

    override fun getViewAt(p0: Int): RemoteViews {
        return RemoteViews(context.packageName, R.layout.item_event).apply {
            setTextViewText(R.id.tvDateEventItem, widgetItems[p0].date)
            setTextViewText(R.id.tvNameEventItem, widgetItems[p0].name)
            setTextViewText(R.id.tvLocationEventItem, widgetItems[p0].location)
            setTextViewText(R.id.tvDescEventItem, widgetItems[p0].description)
            var image = widgetItems[p0].image
            setImageViewResource(R.id.ivEventItem, context.resources.getIdentifier(image, "drawable", context.packageName))
        }
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(p0: Int): Long {
        return 1L
    }

    override fun hasStableIds(): Boolean {
        return true
    }

}
