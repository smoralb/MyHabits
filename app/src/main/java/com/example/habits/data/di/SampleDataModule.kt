package com.example.habits.data.di

import com.example.habits.data.SampleApi
import com.example.habits.domain.repository.SampleDataRepository
import com.example.habits.data.repository.SampleDataRepositoryImpl
import com.example.habits.data.repository.mapper.SampleDataMapper
import com.example.habits.data.repository.mapper.SampleDataMapperImpl
import com.example.habits.data.source.SampleDataRemoteSource
import com.example.habits.data.source.SampleDataRemoteSourceImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val sampleDataModule = module {

    single<SampleDataMapper> { SampleDataMapperImpl() }

    single<SampleDataRemoteSource> { SampleDataRemoteSourceImpl(get(), get()) }

    single<SampleDataRepository> { SampleDataRepositoryImpl(get()) }

    single { get<Retrofit>().create(SampleApi::class.java) }

}