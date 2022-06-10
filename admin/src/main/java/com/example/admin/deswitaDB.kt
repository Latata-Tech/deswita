package com.example.admin

import android.net.Uri
import android.provider.BaseColumns

object deswitaDB {
    class EventTable : BaseColumns {
        companion object {
            const val TABLE_EVENT = "event"
            const val COLUMN_ID = "id"
            const val COLUMN_IMAGE = "image"
            const val COLUMN_DATE = "date"
            const val COLUMN_NAME = "name"
            const val COLUMN_LOCATION = "location"
            const val COLUMN_DESCRIPTION = "description"
        }
    }

    class ContentProviderURI {
        companion object {
            val AUTHORITY = "com.example.deswita.provider.provider.ContentProvider"
            val EVENT_TABLE = EventTable.TABLE_EVENT
            val CONTENT_URI = Uri.parse("content://$AUTHORITY/$EVENT_TABLE")
        }
    }
}