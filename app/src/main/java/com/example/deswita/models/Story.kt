package com.example.deswita.models

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Story(
    var id: Int,
    var name: String,
    var description: String,
    var contentText: String,
    var contentImage: Bitmap,
    var profile: Bitmap,
    var likeTotal: Int,
    var commentTotal: Int
) : Parcelable