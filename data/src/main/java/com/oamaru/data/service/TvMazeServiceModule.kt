package com.oamaru.data.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.tvmaze.com/"
class TvMazeServiceModule {

    private val client by lazy {
        OkHttpClient.Builder()
            .connectTimeout(300L, TimeUnit.SECONDS)
            .readTimeout(300L, TimeUnit.SECONDS)
            .writeTimeout(300L, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    private val interceptor by lazy {
        Interceptor { chain ->
            chain.proceed(
                chain.request().newBuilder().apply {
                    addHeader("accept", "application/json")
                }.build()
            )
        }
    }

    fun tvMazeModule(): Module {
        return module {
            single<TvMazeService> {
                Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .client(client)
                    .build()
                    .create(TvMazeService::class.java)
            }
        }
    }
}
