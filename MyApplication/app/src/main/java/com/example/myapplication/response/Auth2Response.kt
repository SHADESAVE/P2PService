package com.example.myapplication.response

import com.google.gson.annotations.SerializedName

class Auth2Response (
    @SerializedName("percent") val percent: Double,
    @SerializedName("sum") val sum: Double
)