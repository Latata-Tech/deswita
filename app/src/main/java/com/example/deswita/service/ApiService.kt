package com.example.deswita.service

import com.example.deswita.models.weatherResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/data/2.5/weather?q=medan&appid=46539220e13949489ac7eb46ca4e481e")
    fun getWeather(): Call<weatherResponse>
}