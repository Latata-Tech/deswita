package com.example.deswita.utils

import android.net.Uri
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import com.example.deswita.utils.random

object Storage {
    val storage = FirebaseStorage.getInstance()
    val storageRef = storage.reference

    fun getImage(image: String,callback: (url: String)-> Unit) {
        storageRef.child(image).downloadUrl.addOnSuccessListener { uri ->
            callback(uri.toString())
        }
    }

    fun uploadImage(image: Uri, callback: (url: String) -> Unit) {
        val ref = storageRef.child("${(0..9999).random()}_" + "${(0..999).random()}_" + "${(0..9999).random()}.jpg" )
        val uploadTask = ref.putFile(image)
        uploadTask.addOnFailureListener {

        }.addOnCompleteListener {
            if(it.isSuccessful) {
                ref.downloadUrl.addOnSuccessListener { uri ->
                    callback(uri.toString())
                }
            }
        }

    }
}