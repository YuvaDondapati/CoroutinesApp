package com.yuvademos.coroutinesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yuvademos.coroutinesapp.repository.PlayListRepository

class PlayListViewmodelFactory(private val repo:PlayListRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlayListViewModel(repo) as T
    }

}