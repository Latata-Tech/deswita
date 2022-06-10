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
    fun viewAllData(destinationId: Int): List<Review> {
        val reviews = ArrayList<Review>()
        val SELECT_NAME = "SELECT * FROM ${deswitaDB.UserReviewTable.TABLE_USER_REVIEW} WHERE ${deswitaDB.UserReviewTable.COLUMN_DESTINATION_ID} = ${destinationId} "
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(SELECT_NAME, null)
        } catch (e: SQLException) {
            return ArrayList()
        }
        if (cursor.moveToFirst()) {
            do {
                val content = cursor.getString( cursor.getColumnIndex(deswitaDB.UserReviewTable.COLUMN_CONTENT) )
                val rating = cursor.getFloat( cursor.getColumnIndex(deswitaDB.UserReviewTable.COLUMN_RATING) )
                val destinationId = cursor.getInt( cursor.getColumnIndex(deswitaDB.UserReviewTable.COLUMN_DESTINATION_ID) )
                val userId = cursor.getInt( cursor.getColumnIndex(deswitaDB.UserReviewTable.COLUMN_USER_ID) )
                val createdAt = cursor.getString( cursor.getColumnIndex(deswitaDB.UserReviewTable.COLUMN_CREATED_AT) )
                val id = cursor.getInt(cursor.getColumnIndex(deswitaDB.UserReviewTable.COLUMN_ID))
                reviews.add(Review(id,"Jhon doe","user_1",createdAt,content,rating,destinationId,userId))

            } while (cursor.moveToNext())
        }
        print(reviews)
        return reviews
    }

    fun viewAllReviewOnDestinantion(): List<String> {
        val nameList = ArrayList<String>()
        val SELECT_NAME = "SELECT * " +
                "FROM ${deswitaDB.UserReviewTable.TABLE_USER_REVIEW} WHERE ${deswitaDB.UserReviewTable.COLUMN_DESTINATION_ID} = ?"
        return nameList
    }

    fun deleteReview(id: Int): Int{
        val db = this.writableDatabase
        val selection = "${deswitaDB.UserReviewTable.COLUMN_ID}=?"
        val selectionArgs = arrayOf(id.toString())
        val result = db.delete(deswitaDB.UserReviewTable.TABLE_USER_REVIEW,selection,selectionArgs)
        return result
    }

    fun deleteData(nama: String){
        val db = this.writableDatabase
        val selection = "${deswitaDB.UserReviewTable.TABLE_USER_REVIEW} = ?"
        val selectionArgs = arrayOf(nama)
        db.delete(deswitaDB.UserReviewTable.TABLE_USER_REVIEW,selection,selectionArgs)
    }

    fun deleteAllUserReviewOnDestination(destinationId: Int) {
        var db = this.writableDatabase
        val selection = "${deswitaDB.UserReviewTable.COLUMN_DESTINATION_ID} = ?"
        val selectionArgs = arrayOf(destinationId.toString())
        db.delete(deswitaDB.UserReviewTable.TABLE_USER_REVIEW, selection, selectionArgs)
    }

    fun beginUserReviewTransaction() {
        this.writableDatabase.beginTransaction()
    }

    fun successUserReviewTransaction() {
        this.writableDatabase.setTransactionSuccessful()
    }

    fun endUserReviewTransaction() {
        this.writableDatabase.endTransaction()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addUserReviewTransaction(review : Review) {
        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted = current.format(formatter)
        val sqlString = "INSERT INTO ${deswitaDB.UserReviewTable.TABLE_USER_REVIEW}" +
                "(${deswitaDB.UserReviewTable.COLUMN_CONTENT}" +
                ",${deswitaDB.UserReviewTable.COLUMN_RATING}" +
                ",${deswitaDB.UserReviewTable.COLUMN_DESTINATION_ID}" +
                ",${deswitaDB.UserReviewTable.COLUMN_USER_ID}" +
                ",${deswitaDB.UserReviewTable.COLUMN_CREATED_AT}" +
                ",${deswitaDB.UserReviewTable.COLUMN_UPDATED_AT}) VALUES (?, ?, ?, ?, ?, ?)"
        val myStatement = this.writableDatabase.compileStatement(sqlString)
        myStatement.bindString(1, review.content)
        myStatement.bindDouble(2, review.rating.toDouble())
        myStatement.bindLong(3, review.destination_id.toLong())
        myStatement.bindLong(4, review.user_id.toLong())
        myStatement.bindString(5, formatted)
        myStatement.bindString(6, formatted)
        myStatement.execute()
        myStatement.clearBindings()
    }
}