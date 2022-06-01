package com.example.deswita.models

data class Review (
    var name: String = "",
    var image: String = "",
    var date: String = "",
    var content: String = "",
    var rating: Float = 0f,
    var destination_id: Int = 0,
    var user_id: Int = 0
        )