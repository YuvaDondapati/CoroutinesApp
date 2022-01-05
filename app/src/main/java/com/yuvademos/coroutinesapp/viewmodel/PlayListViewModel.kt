package com.yuvademos.coroutinesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.yuvademos.coroutinesapp.repository.PlayListRepository

class PlayListViewModel(val repo: PlayListRepository) : ViewModel() {

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
        emitSource(repo.getPlayLists().asLiveData())
    }
}