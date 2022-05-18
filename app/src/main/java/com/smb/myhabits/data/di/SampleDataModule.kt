package com.smb.myhabits.data.di

import com.smb.myhabits.domain.repository.SampleDataRepository
import com.smb.myhabits.data.repository.SampleDataRepositoryImpl
import com.smb.myhabits.data.repository.mapper.SampleDataMapper
import com.smb.myhabits.data.repository.mapper.SampleDataMapperImpl
import com.smb.myhabits.data.source.SampleDataRemoteSource
import com.smb.myhabits.data.source.SampleDataRemoteSourceImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val sampleDataModule = module {

    single<SampleDataMapper> { SampleDataMapperImpl() }

    single<SampleDataRemoteSource> { SampleDataRemoteSourceImpl(get(), get()) }

    single<SampleDataRepository> { SampleDataRepositoryImpl(get()) }

    single { get<Retrofit>().create(com.smb.myhabits.data.SampleApi::class.java) }

}