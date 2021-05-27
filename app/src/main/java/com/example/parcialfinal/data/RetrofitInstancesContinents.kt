package com.example.parcialfinal.data

import com.example.parcialfinal.data.api.ContinentsApi
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstancesContinents {

    private fun getInstance(): Retrofit {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://geo-services-by-mvpc-com.p.rapidapi.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit
    }

    fun continentsApi(): ContinentsApi {
        return getInstance().create(ContinentsApi::class.java)
    }
}