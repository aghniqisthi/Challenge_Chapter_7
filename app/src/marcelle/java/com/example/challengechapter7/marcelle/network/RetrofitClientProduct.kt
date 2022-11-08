package com.example.challengechapter7.marcelle.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientProduct{
    const val BASE_URL = "https://makeup-api.herokuapp.com/"

    private  val logging : HttpLoggingInterceptor
        get(){
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }

    private val clint = OkHttpClient.Builder().addInterceptor(logging).build()

    val instance : RestfulAPIProduct by lazy {
        val retrofit = Retrofit.Builder().baseUrl(RetrofitClientUser.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        retrofit.create(RestfulAPIProduct::class.java)
    }

}