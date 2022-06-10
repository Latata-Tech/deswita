package com.example.deswita.utils

import android.annotation.SuppressLint
import android.content.ContentValues
import deswitaDB.deswitaDB
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.deswita.models.Event
import com.example.deswita.models.Review
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EventHelperDB(context: Context) : SQLiteOpenHelper (context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object {
        private val DATABASE_NAME = "deswita.db"
        private val DATABASE_VERSION = 1;
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var CREATE_EVENT_TABLE = ("CREATE TABLE ${deswitaDB.EventTable.TABLE_EVENT} " +
                "(${deswitaDB.EventTable.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${deswitaDB.EventTable.COLUMN_NAME} TEXT," +
                "${deswitaDB.EventTable.COLUMN_DATE} TEXT," +
                "${deswitaDB.EventTable.COLUMN_DESCRIPTION} TEXT," +
                "${deswitaDB.EventTable.COLUMN_IMAGE} TEXT," +
                "${deswitaDB.EventTable.COLUMN_LOCATION} TEXT," )
        db!!.execSQL(CREATE_EVENT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(
            "DROP TABLE IF EXISTS " +
                    "${deswitaDB.EventTable.TABLE_EVENT}"
        )
        onCreate(db)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addEvent(event: Event): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(deswitaDB.EventTable.COLUMN_NAME, event.name)
            put(deswitaDB.EventTable.COLUMN_DATE, event.date)
            put(deswitaDB.EventTable.COLUMN_DESCRIPTION, event.description)
            put(deswitaDB.EventTable.COLUMN_IMAGE, event.image)
            put(deswitaDB.EventTable.COLUMN_LOCATION, event.location)
        }
        val success = db.insert(
            deswitaDB.EventTable.TABLE_EVENT,
            null, contentValues
        )
        db.close()
        return success
    }

    @SuppressLint("Range")
    fun getEvents(): List<Event> {
        val events = ArrayList<Event>()
        val QUERY = "SELECT * FROM ${deswitaDB.EventTable.TABLE_EVENT} "
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(QUERY, null)
        } catch (e: SQLException) {
            return ArrayList()
        }
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(deswitaDB.EventTable.COLUMN_ID))
                val name = cursor.getString( cursor.getColumnIndex(deswitaDB.EventTable.COLUMN_NAME) )
                val image = cursor.getString( cursor.getColumnIndex(deswitaDB.EventTable.COLUMN_IMAGE) )
                val date = cursor.getString( cursor.getColumnIndex(deswitaDB.EventTable.COLUMN_DATE) )
                val description = cursor.getString( cursor.getColumnIndex(deswitaDB.EventTable.COLUMN_DESCRIPTION) )
                val location = cursor.getString( cursor.getColumnIndex(deswitaDB.EventTable.COLUMN_LOCATION) )

                events.add(Event(id,image,date,name,location,description))

            } while (cursor.moveToNext())
        }
        print(events)
        return events
    }


}