package com.example.deswita.utils

import android.content.Context
import android.content.SharedPreferences

class SharePrefHelper (context: Context, fileName: String) {
    val HISTORY = "HISTORY"

    private lateinit var myPref: SharedPreferences
    init {
        myPref =  context.getSharedPreferences(fileName,Context.MODE_PRIVATE)
    }

    inline fun SharedPreferences.editMe(operator: (SharedPreferences.Editor) -> Unit) {
        val editMe: SharedPreferences.Editor = edit()
        operator(editMe)
        editMe.apply()
    }

    var histories: Set<String>?
        get() = myPref.getStringSet(HISTORY,null)
        set(value) {
            myPref.editMe {
                it.putStringSet(HISTORY,value)
            }
        }
}