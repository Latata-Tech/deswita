package com.example.deswita.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import com.example.deswita.utils.EventHelperDB
import deswitaDB.deswitaDB

class ContentProvider: ContentProvider() {

    private var db: EventHelperDB? = null

    override fun onCreate(): Boolean {
        db = EventHelperDB(context!!)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        var queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = deswitaDB.EventTable.TABLE_EVENT
        var cursor: Cursor = queryBuilder.query(db?.readableDatabase,projection,null,null,null,null,null)
        cursor.setNotificationUri(context?.contentResolver,uri)
        return cursor
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }

    companion object {
        val AUTHORITY = "com.example.deswita.provider.provider.ContentProvider"
        val EVENT_TABLE = deswitaDB.EventTable.TABLE_EVENT
        val CONTENT_URI = Uri.parse("content://$AUTHORITY/$EVENT_TABLE")
    }
}