package com.yuvademos.coroutinesapp.repository

import com.yuvademos.coroutinesapp.model.PlayList
import com.yuvademos.coroutinesapp.network.PlayListService
import kotlinx.coroutines.flow.Flow

class PlayListRepository(private val service: PlayListService) {

    suspend fun getPlayLists(): Flow<Result<List<PlayList>>> {
        return service.fetchPlayLists()
    }
}