package com.yuvademos.coroutinesapp.network

import com.yuvademos.coroutinesapp.model.PlayList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException

class PlayListService(private val api: PlayListApi) {
    //    @GET("/playlists")
    suspend fun fetchPlayLists(): Flow<Result<List<PlayList>>> {
        return flow {
            emit(Result.success(api.fetchAllPlayLists()))
        }.catch {
            emit(Result.failure(RuntimeException("something went wrong")))
        }
    }
}