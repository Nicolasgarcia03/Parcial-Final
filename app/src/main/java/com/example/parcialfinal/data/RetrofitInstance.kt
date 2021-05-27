package com.example.parcialfinal.data

import com.example.parcialfinal.data.api.CountryApi
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    private fun getInstance(): Retrofit {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://ajayakv-rest-countries-v1.p.rapidapi.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit
    }

    fun countryApi(): CountryApi {
        return getInstance().create(CountryApi::class.java)
    }
}