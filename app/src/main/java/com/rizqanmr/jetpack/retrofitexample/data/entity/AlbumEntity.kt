package com.rizqanmr.jetpack.retrofitexample.data.entity


import com.google.gson.annotations.SerializedName

class AlbumEntity : ArrayList<AlbumItem>()

data class AlbumItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)