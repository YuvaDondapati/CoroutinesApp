package com.yuvademos.coroutinesapp.di

import com.yuvademos.coroutinesapp.network.PlayListApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module(override = true) {

    single {
        provideOkHttp()
    }
    single {
        provideRetrofit(get())
    }
    single {
        provideApi(get())
    }
}

fun provideApi(retrofit: Retrofit): PlayListApi {
    return retrofit.create(PlayListApi::class.java)
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("http://192.168.0.104:3000/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder()
    .build()
