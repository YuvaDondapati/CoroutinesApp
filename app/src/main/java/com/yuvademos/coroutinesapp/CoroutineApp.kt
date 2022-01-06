package com.yuvademos.coroutinesapp

import android.app.Application
import com.yuvademos.coroutinesapp.di.networkModule
import com.yuvademos.coroutinesapp.di.utilsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CoroutineApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(level = Level.ERROR)
            androidContext(this@CoroutineApp)
            modules(listOf(networkModule, utilsModule))
        }
    }
}