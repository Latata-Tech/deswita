package com.example.deswita.models

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchRecomendation(
    var id: Int,
    var image: Bitmap,
    var name: String,
    var location: String,
    var distance: List<Int>
) : Parcelable
