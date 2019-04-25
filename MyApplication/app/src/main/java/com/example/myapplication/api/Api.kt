package com.example.myapplication.api

import com.example.myapplication.response.Auth2Response
import com.example.myapplication.response.AuthResponse
import com.example.myapplication.response.BaseResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("/login")
    fun login(
        @Field("login") login: String,
        @Field("password") password: String,
        @Field("investor") investor: String
    ): Call<BaseResponse<String>>

    @FormUrlEncoded
    @POST("/investor/pay")
    fun fillBalanceInvestor(
        @Field("id") id: String,
        @Field("sum") sum: Double
    ): Call<BaseResponse<Double>>


    @FormUrlEncoded
    @POST("/borrower/pay")
    fun fillBalanceBorrower(
        @Field("id") id: String,
        @Field("sum") sum: Double
    ): Call<BaseResponse<Double>>

    @FormUrlEncoded
    @POST("/borrower/ask")
    fun borrowerAsk(
        @Field("id") id: String,
        @Field("sum") sum: Double,
        @Field("term") term: Int
    ): Call<BaseResponse<AuthResponse>>

    @FormUrlEncoded
    @POST("/borrower/ok")
    fun borrowerOk(
        @Field("server_offer_id") server_offer_id: String,
        @Field("id") id: String
    ): Call<BaseResponse<Boolean>>

    @FormUrlEncoded
    @POST("/investor/offer")
    fun investorOffer(
        @Field("id") id: String,
        @Field("sum") sum: Double
    ): Call<BaseResponse<Auth2Response>>

    @GET("/investor/balance")
    fun viewBalanceInvestor(
        @Query ("id") id: String
    ): Call<BaseResponse<Double>>

    @GET("/borrower/balance")
    fun viewBalanceBorrower(
        @Query ("id") id: String
    ): Call<BaseResponse<Double>>

    companion object Factory {
        private val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()

        fun create(): Api {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl("http://192.168.12.216:8080/")
                .build()
            return retrofit.create(Api::class.java)
        }
    }
}