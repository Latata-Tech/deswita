package com.example.deswita.utils

import java.util.*

fun String.CapitalizeAllWord(): String =
    split(" ").map { it.lowercase(Locale.getDefault())
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } }
        .joinToString(" ")

fun String.CapitalizeFirstWord(): String =
    this.lowercase(Locale.getDefault()).replaceFirstChar { if(it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }