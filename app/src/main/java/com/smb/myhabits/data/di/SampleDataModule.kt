package com.smb.myhabits.data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.smb.myhabits.data.repository.SampleDataRepositoryImpl
import com.smb.myhabits.data.repository.mapper.SampleDataMapper
import com.smb.myhabits.data.repository.mapper.SampleDataMapperImpl
import com.smb.myhabits.data.source.SampleDataRemoteSource
import com.smb.myhabits.data.source.SampleDataRemoteSourceImpl
import com.smb.myhabits.domain.repository.SampleDataRepository
import org.koin.dsl.module

val sampleDataModule = module {

    single<SampleDataMapper> { SampleDataMapperImpl() }

    single<SampleDataRemoteSource> { SampleDataRemoteSourceImpl(get(), get()) }

    single<SampleDataRepository> { SampleDataRepositoryImpl(get()) }

    single { FirebaseFirestore.getInstance() }

}