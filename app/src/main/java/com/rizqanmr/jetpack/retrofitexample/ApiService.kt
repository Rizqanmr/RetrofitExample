package com.rizqanmr.jetpack.retrofitexample

import com.rizqanmr.jetpack.retrofitexample.data.entity.AlbumEntity
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/albums")
    suspend fun getAlbums() : Response<AlbumEntity>
}