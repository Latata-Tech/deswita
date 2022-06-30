package com.example.deswita.utils

import android.annotation.SuppressLint
import com.google.firebase.firestore.FirebaseFirestore

object Firestore {

    @SuppressLint("StaticFieldLeak")
    val db = FirebaseFirestore.getInstance()

    fun ok() {

    }

}