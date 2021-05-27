package com.example.parcialfinal.data.api

import com.example.parcialfinal.data.model.ResultContinents
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ContinentsApi {

    @Headers("x-rapidapi-key: 60e38660acmsh2e1111f54d583edp12d6ddjsn99b17de7dfaa")
    @GET("/continents")
    fun getContinents(@Query("language") en: String): Call<ResultContinents>
}