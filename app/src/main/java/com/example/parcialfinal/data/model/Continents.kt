package com.example.parcialfinal.data.model

import com.google.gson.annotations.SerializedName

data class Continents(
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("name_locale")
    val name_locale: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double

)