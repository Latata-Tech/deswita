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
import com.example.deswita.models.Review
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UserReviewHelperDB(context: Context) : SQLiteOpenHelper (context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object {
        private val DATABASE_NAME = "deswita.db"
        private val DATABASE_VERSION = 1;
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var CREATE_USER_REVIEW_TABLE = ("CREATE TABLE ${deswitaDB.UserReviewTable.TABLE_USER_REVIEW} " +
                "(${deswitaDB.UserReviewTable.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${deswitaDB.UserReviewTable.COLUMN_CONTENT} TEXT," +
                "${deswitaDB.UserReviewTable.COLUMN_RATING} REAL," +
                "${deswitaDB.UserReviewTable.COLUMN_DESTINATION_ID} INTEGER," +
                "${deswitaDB.UserReviewTable.COLUMN_USER_ID} INTEGER," +
                "${deswitaDB.UserReviewTable.COLUMN_CREATED_AT} TEXT," +
                "${deswitaDB.UserReviewTable.COLUMN_UPDATED_AT} TEXT)")
        db!!.execSQL(CREATE_USER_REVIEW_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(
            "DROP TABLE IF EXISTS " +
                    "${deswitaDB.UserReviewTable.TABLE_USER_REVIEW}"
        )
        onCreate(db)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addUserReview(review: Review): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            val current = LocalDateTime.now()

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            val formatted = current.format(formatter)
            put(deswitaDB.UserReviewTable.COLUMN_CONTENT, review.content)
            put(deswitaDB.UserReviewTable.COLUMN_RATING, review.rating)
            put(deswitaDB.UserReviewTable.COLUMN_DESTINATION_ID, review.destination_id)
            put(deswitaDB.UserReviewTable.COLUMN_USER_ID, review.user_id)
            put(deswitaDB.UserReviewTable.COLUMN_CREATED_AT, formatted)
            put(deswitaDB.UserReviewTable.COLUMN_UPDATED_AT, formatted)
        }
        val success = db.insert(
            deswitaDB.UserReviewTable.TABLE_USER_REVIEW,
            null, contentValues
        )
        db.close()
        return success
    }

    @SuppressLint("Range")
    fun viewAllData(): List<String> {
        val nameList = ArrayList<String>()
        val SELECT_NAME = "SELECT ${deswitaDB.UserReviewTable.COLUMN_CONTENT} " +
                "FROM ${deswitaDB.UserReviewTable.TABLE_USER_REVIEW}"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(SELECT_NAME, null)
        } catch (e: SQLException) {
            //db.execSQL(SELECT_NAME)
            return ArrayList()
        }
        var userNama: String = ""
        if (cursor.moveToFirst()) {
            do {
                userNama = cursor.getString(
                    cursor.getColumnIndex(deswitaDB.UserReviewTable.COLUMN_CONTENT)
                )
                nameList.add(userNama)
            } while (cursor.moveToNext())
        }
        print(nameList)
        return nameList
    }

    fun viewAllReviewOnDestinantion(): List<String> {
        val nameList = ArrayList<String>()
        val SELECT_NAME = "SELECT * " +
                "FROM ${deswitaDB.UserReviewTable.TABLE_USER_REVIEW} WHERE ${deswitaDB.UserReviewTable.COLUMN_DESTINATION_ID} = ?"
        return nameList
    }

    fun deleteData(nama: String){
        val db = this.writableDatabase
        val selection = "${deswitaDB.UserReviewTable.TABLE_USER_REVIEW} = ?"
        val selectionArgs = arrayOf(nama)
        db.delete(deswitaDB.UserReviewTable.TABLE_USER_REVIEW,selection,selectionArgs)
    }
}