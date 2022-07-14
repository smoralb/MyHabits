package com.smb.ft_home.data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.smb.ft_home.data.repository.HomeRepositoryImpl
import com.smb.ft_home.data.repository.mapper.HomeDataMapper
import com.smb.ft_home.data.repository.mapper.HomeDataMapperImpl
import com.smb.ft_home.data.source.HomeRemoteSource
import com.smb.ft_home.data.source.HomeRemoteSourceImpl
import com.smb.ft_home.domain.repository.HomeRepository
import org.koin.dsl.module

val homeDataModule = module {

    single<HomeDataMapper> { HomeDataMapperImpl() }

    single<HomeRemoteSource> { HomeRemoteSourceImpl(get(), get(), get()) }

    single<HomeRepository> { HomeRepositoryImpl(get()) }

    single { FirebaseFirestore.getInstance() }

}