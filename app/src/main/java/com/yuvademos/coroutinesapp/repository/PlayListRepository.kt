package com.yuvademos.coroutinesapp.repository

import com.yuvademos.coroutinesapp.model.PlayList
import com.yuvademos.coroutinesapp.network.PlayListApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlayListRepository(private val api: PlayListApi) {

    suspend fun getPlayLists(): Flow<Result<List<PlayList>>> {
        return api.fetchPlayLists()
    }
}