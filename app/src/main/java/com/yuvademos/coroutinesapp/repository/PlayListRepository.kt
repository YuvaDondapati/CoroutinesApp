package com.yuvademos.coroutinesapp.repository

import com.yuvademos.coroutinesapp.model.PlayList
import kotlinx.coroutines.flow.Flow

class PlayListRepository {

   suspend fun getPlayLists() : Flow<Result<List<PlayList>>>{
       TODO("Not yet implemented")
    }
}