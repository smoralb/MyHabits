package com.example.baseapplication.data.di

import com.example.baseapplication.data.SampleApi
import com.example.baseapplication.data.repository.SampleDataRepository
import com.example.baseapplication.data.repository.SampleDataRepositoryImpl
import com.example.baseapplication.data.repository.mapper.SampleDataMapper
import com.example.baseapplication.data.repository.mapper.SampleDataMapperImpl
import com.example.baseapplication.data.source.SampleDataRemoteSource
import com.example.baseapplication.data.source.SampleDataRemoteSourceImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val sampleDataModule = module {

    single<SampleDataMapper> { SampleDataMapperImpl() }

    single<SampleDataRemoteSource> { SampleDataRemoteSourceImpl(get(), get()) }

    single<SampleDataRepository> { SampleDataRepositoryImpl(get()) }

    single { get<Retrofit>().create(SampleApi::class.java) }

}