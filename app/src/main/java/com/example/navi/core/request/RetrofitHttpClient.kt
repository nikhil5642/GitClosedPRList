package com.example.navi.core.request

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHttpClient {
    private val API_BASE_URL = "https://api.github.com"

    private val logging:HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private val httpClient: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(logging);


    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    private val apiService: ApiService = retrofit.create(ApiService::class.java)


    fun getApiService(): ApiService{
        return apiService
    }
}