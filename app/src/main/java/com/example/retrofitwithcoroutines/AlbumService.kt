package com.example.retrofitwithcoroutines

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumService {

    @GET("/albums")
    suspend fun getAlbums() : Response<Albums>

    @GET("/albums") // https://jsonplaceholder.typicode.com/albums?userId=
    suspend fun getSortedAlbums(@Query("userId") userId: Int) : Response<Albums>

    @GET("/albums/{id}")
    suspend fun getAblum(@Path(value = "id")albumId:Int) : Response<AlbumsItem>

}