package com.example.parcialfinal.data.model

import com.google.gson.annotations.SerializedName

data class Country(

        @SerializedName("name")
        val name: String,

        @SerializedName("alpha2Code")
        val alpha2Code: String,

        @SerializedName("region")
        val region: String,

        @SerializedName("subregion")
        val subregion: String,

        @SerializedName("population")
        val population: Int,

        @SerializedName("area")
        val area: Double)