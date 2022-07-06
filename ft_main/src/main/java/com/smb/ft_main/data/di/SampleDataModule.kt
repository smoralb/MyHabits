package com.smb.ft_main.data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.smb.ft_main.data.repository.SampleDataRepositoryImpl
import com.smb.ft_main.data.repository.mapper.SampleDataMapper
import com.smb.ft_main.data.repository.mapper.SampleDataMapperImpl
import com.smb.ft_main.data.source.SampleDataRemoteSource
import com.smb.ft_main.data.source.SampleDataRemoteSourceImpl
import com.smb.ft_main.domain.repository.SampleDataRepository
import org.koin.dsl.module

val sampleDataModule = module {

    single<SampleDataMapper> { SampleDataMapperImpl() }

    single<SampleDataRemoteSource> { SampleDataRemoteSourceImpl(get(), get()) }

    single<SampleDataRepository> { SampleDataRepositoryImpl(get()) }

    single { FirebaseFirestore.getInstance() }

}