package com.example.deswita.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comment (
    var name: String,
    var comment: String,
    var date: String
        ) : Parcelable