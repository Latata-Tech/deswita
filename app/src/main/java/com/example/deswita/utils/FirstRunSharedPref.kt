package com.example.deswita.utils

import android.content.Context
import android.content.SharedPreferences

class FirstRunSharedPref (context: Context){
    private val keyPref = "FIRST_RUN"
    private val mySharedPref : SharedPreferences = context.getSharedPreferences("SharePrefFile", Context.MODE_PRIVATE)
    var firstRun : Boolean
    get() = mySharedPref.getBoolean(keyPref, true)
    set(value) { mySharedPref.edit().putBoolean(keyPref, value).commit() }
}