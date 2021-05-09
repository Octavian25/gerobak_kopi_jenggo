package com.example.gerobak_kopi_jenggo.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retro {
    val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    val client : OkHttpClient = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
    }.build()
    fun postRetroClient(url :String): Retrofit{
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create(gson)).client(client).build()
    }
}