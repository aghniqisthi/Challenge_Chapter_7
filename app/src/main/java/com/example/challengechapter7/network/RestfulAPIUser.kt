package com.example.challengechapter7.network

import com.example.challengechapter7.model.ResponseDataUserItem
import com.example.challengechapter7.model.User
import com.example.challengechapter7.model.UserEdit
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