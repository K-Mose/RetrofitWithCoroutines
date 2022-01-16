package com.example.retrofitwithcoroutines

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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