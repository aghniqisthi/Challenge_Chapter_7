package com.example.challengechapter7.marcelle.network

import com.example.challengechapter7.marcelle.model.ResponseDataProductItem
import retrofit2.Call
import retrofit2.http.GET

interface RestfulAPIProduct {
    @GET("api/v1/products.json?brand=marcelle")
    fun getAllProduct() : Call<List<ResponseDataProductItem>>
}