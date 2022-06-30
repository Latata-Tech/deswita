package com.example.deswita.models

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Parcelable
import com.google.firebase.Timestamp
import kotlinx.parcelize.Parcelize

@Parcelize
data class Story(
    var id: Int,
    var name: String,
    var description: String,
    var contentText: String,
    var contentImage: String,
    var profile: String,
    var likeTotal: Int,
    var commentTotal: Int,
    var comments: Int,
    var date: Timestamp? = null
) : Parcelable