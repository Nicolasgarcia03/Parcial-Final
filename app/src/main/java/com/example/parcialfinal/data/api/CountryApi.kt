package com.example.parcialfinal.data.api

import com.example.parcialfinal.data.model.Country
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface CountryApi {

    @Headers("x-rapidapi-key: 60e38660acmsh2e1111f54d583edp12d6ddjsn99b17de7dfaa")
    @GET("rest/v1/all")
    fun getCountries():Call<List<Country>>
}