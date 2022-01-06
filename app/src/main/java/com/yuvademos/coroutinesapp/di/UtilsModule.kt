package com.yuvademos.coroutinesapp.di

import com.yuvademos.coroutinesapp.network.PlayListService
import com.yuvademos.coroutinesapp.repository.PlayListRepository
import com.yuvademos.coroutinesapp.viewmodel.PlayListViewModelFactory
import org.koin.dsl.module

val utilsModule = module(override = true) {

    single {
        PlayListService(api = get())
    }
    single {
        PlayListRepository(service = get())
    }
    single {
        PlayListViewModelFactory(repo = get())
    }
}