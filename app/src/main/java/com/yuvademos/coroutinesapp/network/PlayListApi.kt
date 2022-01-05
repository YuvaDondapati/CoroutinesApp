package com.yuvademos.coroutinesapp.network

import com.yuvademos.coroutinesapp.model.PlayList
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface PlayListApi {
    @GET("/playlists")
    fun fetchPlayLists() : Flow<List<PlayList>>
}