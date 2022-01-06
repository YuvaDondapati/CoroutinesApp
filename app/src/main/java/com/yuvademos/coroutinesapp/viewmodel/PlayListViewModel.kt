package com.yuvademos.coroutinesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.yuvademos.coroutinesapp.repository.PlayListRepository
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach

class PlayListViewModel(val repo: PlayListRepository) : ViewModel() {

    val loader = MutableLiveData<Boolean>()
    //one way of assinging playlists using viewmodel scope
    /*val playLists = MutableLiveData<Result<List<PlayList>>>()
    init {
        viewModelScope.launch {
            repo.getPlayLists().collect{
                playLists.value = it
            }
        }
    }*/

    //second way using livedata scope
    val playLists = liveData {
        loader.value = true
        emitSource(repo.getPlayLists()
            .onCompletion {
                loader.value = false
            }
            .asLiveData())
    }
}