package com.example.deswita.ui.mainmenu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel : ViewModel() {

    private var _date = MutableLiveData<String>()
    val date: LiveData<String> get() = _date

    init {
        setDate(Date())
    }

    fun setDate(date: Date) {
        val sdf = SimpleDateFormat("dd MMMM yyyy")
        val currentDate = sdf.format(date)
        _date.postValue(currentDate)
    }

    fun getDate(): Long {
        val sdf = SimpleDateFormat("dd MMMM yyyy").parse(date.value)
        return sdf.time
    }

}