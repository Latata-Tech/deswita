package com.example.admin

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import com.example.admin.deswitaDB.EventTable.Companion.COLUMN_DATE
import com.example.admin.deswitaDB.EventTable.Companion.COLUMN_DESCRIPTION
import com.example.admin.deswitaDB.EventTable.Companion.COLUMN_ID
import com.example.admin.deswitaDB.EventTable.Companion.COLUMN_IMAGE
import com.example.admin.deswitaDB.EventTable.Companion.COLUMN_LOCATION
import com.example.admin.deswitaDB.EventTable.Companion.COLUMN_NAME
import com.example.deswita.models.Event

class EventTransaction(context: Context) {

    private val contentResolver: ContentResolver = context.contentResolver

    @SuppressLint("Range")
    fun getEvents(): List<Event> {
        val events = ArrayList<Event>()
        var projection = arrayOf(COLUMN_ID, COLUMN_NAME,COLUMN_DATE, COLUMN_DESCRIPTION,
            COLUMN_IMAGE, COLUMN_LOCATION)
        var cursor = contentResolver.query(deswitaDB.ContentProviderURI.CONTENT_URI,projection,null,null,null,null)
        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex(deswitaDB.EventTable.COLUMN_ID))
                    val name = cursor.getString( cursor.getColumnIndex(deswitaDB.EventTable.COLUMN_NAME) )
                    val image = cursor.getString( cursor.getColumnIndex(deswitaDB.EventTable.COLUMN_IMAGE) )
                    val date = cursor.getString( cursor.getColumnIndex(deswitaDB.EventTable.COLUMN_DATE) )
                    val description = cursor.getString( cursor.getColumnIndex(deswitaDB.EventTable.COLUMN_DESCRIPTION) )
                    val location = cursor.getString( cursor.getColumnIndex(deswitaDB.EventTable.COLUMN_LOCATION) )

                    events.add(Event(id,image,date,name,location,description))
                }while (cursor.moveToNext())
            }
        }
        return events
    }

}