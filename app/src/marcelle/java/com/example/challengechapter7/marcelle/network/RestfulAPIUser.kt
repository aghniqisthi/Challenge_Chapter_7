package com.example.challengechapter7.marcelle.network

import com.example.challengechapter7.marcelle.model.ResponseDataUserItem
import com.example.challengechapter7.marcelle.model.User
import com.example.challengechapter7.marcelle.model.UserEdit
import retrofit2.Call
import retrofit2.http.*

interface RestfulAPIUser {
    @GET("users")
    fun getAllUser() : Call<List<ResponseDataUserItem>>

    @POST("users")
    fun addUser(@Body request : User) : Call<ResponseDataUserItem>

    @PUT("users/{id}")
    fun putUser(@Path("id") id:Int,
                @Body request: UserEdit
    ) : Call<List<ResponseDataUserItem>>
}