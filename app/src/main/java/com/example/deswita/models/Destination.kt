package com.example.deswita.models

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Destination (
    var image: String,
    var name: String,
    var location: String,
    var isFavorite: Boolean,
    var rating: Double,
    var distance: Double
    ) : Parcelable