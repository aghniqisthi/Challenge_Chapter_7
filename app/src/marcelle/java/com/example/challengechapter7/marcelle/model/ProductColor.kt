package com.example.challengechapter7.marcelle.model


import com.google.gson.annotations.SerializedName

data class ProductColor(
    @SerializedName("colour_name")
    val colourName: String,
    @SerializedName("hex_value")
    val hexValue: String
)