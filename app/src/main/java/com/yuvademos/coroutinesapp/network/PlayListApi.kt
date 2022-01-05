package com.yuvademos.coroutinesapp.network

import com.yuvademos.coroutinesapp.model.PlayList
import retrofit2.http.GET

interface PlayListApi {

    @GET("playlists")
    suspend fun fetchAllPlayLists(): List<PlayList>
}