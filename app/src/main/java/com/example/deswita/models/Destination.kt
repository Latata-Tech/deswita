package com.example.deswita.models

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Destination (
    var image: Bitmap
    ) : Parcelable