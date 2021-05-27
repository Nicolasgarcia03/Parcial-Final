package com.example.parcialfinal.data.model

import com.google.gson.annotations.SerializedName

data class ResultContinents(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: List<Continents>,
    @SerializedName("message")
    val message: String,
    @SerializedName("count")
    val count: Int
)