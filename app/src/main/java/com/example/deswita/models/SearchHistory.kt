package com.example.deswita.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchHistory(
    var id: Int,
    var name: String,
    var location: String
) : Parcelable
