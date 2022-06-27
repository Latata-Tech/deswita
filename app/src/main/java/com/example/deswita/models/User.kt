package com.example.deswita.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: String = "",
    val name: String = "",
    val username: String = "",
    val password: String = ""
) : Parcelable
