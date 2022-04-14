package com.example.deswita.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wind(
    @SerializedName("deg")
    var deg: Int?,
    @SerializedName("gust")
    var gust: Int?,
    @SerializedName("speed")
    var speed: Double?
): Parcelable