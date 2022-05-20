package com.example.deswita.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchHistory(
    var id: String,
    var name: String,
) : Parcelable
