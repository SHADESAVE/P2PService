package com.example.myapplication.response

import com.google.gson.annotations.SerializedName

class BaseResponse<T>(
    @SerializedName("status") val status: Boolean,
    @SerializedName("data") val data: T?,
    @SerializedName("error") val error: ErrorResponse
)