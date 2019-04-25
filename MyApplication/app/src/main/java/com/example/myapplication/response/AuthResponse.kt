package com.example.myapplication.response

import com.google.gson.annotations.SerializedName

class AuthResponse(
    @SerializedName("percent") val percent: Double,
    @SerializedName("term") val term: Int,
    @SerializedName("sum") val sum: Double,
    @SerializedName("id") val id: String
)