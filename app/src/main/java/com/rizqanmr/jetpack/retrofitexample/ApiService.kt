package com.rizqanmr.jetpack.retrofitexample

import com.rizqanmr.jetpack.retrofitexample.data.entity.AlbumEntity
import com.rizqanmr.jetpack.retrofitexample.data.entity.AlbumItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/albums")
    suspend fun getAlbums() : Response<AlbumEntity>

    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId") userId: Int) : Response<AlbumEntity>

    @GET("/albums/{id}")
    suspend fun getAlbum(@Path(value = "id") albumId: Int) : Response<AlbumItem>
}