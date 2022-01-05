package com.yuvademos.coroutinesapp.network

import com.yuvademos.coroutinesapp.model.PlayList
import kotlinx.coroutines.flow.Flow

class PlayListApi {
    //    @GET("/playlists")
    suspend fun fetchPlayLists(): Flow<Result<List<PlayList>>> {
        TODO("not yet implemented")
    }
}