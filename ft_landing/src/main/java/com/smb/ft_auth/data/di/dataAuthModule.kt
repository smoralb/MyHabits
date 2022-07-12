package com.smb.ft_auth.data.di

import com.google.firebase.auth.FirebaseAuth
import com.smb.ft_auth.data.repository.AuthRepositoryImpl
import com.smb.ft_auth.data.source.AuthRemoteDataSource
import com.smb.ft_auth.data.source.AuthRemoteDataSourceImpl
import com.smb.ft_auth.domain.repository.AuthRepository
import org.koin.dsl.module

val dataAuthModule = module {
    single<FirebaseAuth> { FirebaseAuth.getInstance() }
    single<AuthRepository> { AuthRepositoryImpl(source = get()) }
    single<AuthRemoteDataSource> { AuthRemoteDataSourceImpl(auth = get()) }
}