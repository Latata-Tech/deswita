package com.example.deswita.models

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    var id: Int,
    var image: String,
    var date: String,
    var name: String,
    var location: String,
    var description: String,
) : Parcelable