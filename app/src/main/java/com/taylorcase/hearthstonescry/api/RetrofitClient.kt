package com.taylorcase.hearthstonescry.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroClient {

    private const val ROOT_URL = "https://omgvamp-hearthstone-v1.p.mashape.com/"

    private val retrofitInstance: Retrofit
        get() = Retrofit.Builder().baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

    val apiService: HearthstoneApi
        get() = retrofitInstance.create(HearthstoneApi::class.java)
}