package com.yuvademos.coroutinesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yuvademos.coroutinesapp.model.PlayList

class PlayListViewModel : ViewModel() {
    val playLists = MutableLiveData<Result<List<PlayList>>>()
}