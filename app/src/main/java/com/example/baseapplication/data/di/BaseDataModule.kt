package com.example.baseapplication.data.di

import com.example.baseapplication.EMPTY_STRING
import com.example.baseapplication.data.SampleApi
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataModule = module {

    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    fun provideRetrofit(factory: Moshi, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            // Place base url when exists any API
            .baseUrl(EMPTY_STRING)
            .addConverterFactory(MoshiConverterFactory.create(factory))
            .client(client)
            .build()
    }

    fun provideApiInstance(retrofit: Retrofit): SampleApi {
        return retrofit.create(SampleApi::class.java)
    }

    factory { provideHttpClient() }

    factory { provideRetrofit(get(), get()) }

    single { provideApiInstance(get()) }

}