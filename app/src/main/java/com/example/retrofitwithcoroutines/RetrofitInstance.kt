package com.example.retrofitwithcoroutines

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    companion object {
        val BASE_URL = "https://jsonplaceholder.typicode.com"

        val interceptor = HttpLoggingInterceptor().apply {
            /*
             * Basic - Log request and response lines only
             * Body - Logs request and response line amd their respective headers and bodies of the network operation.
             * Header - Logs request and response lines and their respective headers
             */
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS) // 타임아웃 30초로 설정
                .readTimeout(20, TimeUnit.SECONDS) // 2개의 데이터 패킷이 도착하는데 걸리는 최대 시간 차 설정
                .writeTimeout(25, TimeUnit.SECONDS) // 2개의 데이터 패킷을 서버에 보내는데 걸리는 최대 시간 차 설정
        }.build()

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}