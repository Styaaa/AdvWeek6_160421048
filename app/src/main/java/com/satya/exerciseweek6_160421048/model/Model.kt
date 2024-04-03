package com.satya.exerciseweek6_160421048.model

import com.google.gson.annotations.SerializedName

data class Cake(
    val id: String?,
    val name: String?,
    @SerializedName("description")

    val desc: String?,
    val ingredients: String?,
    val images: String?
)
