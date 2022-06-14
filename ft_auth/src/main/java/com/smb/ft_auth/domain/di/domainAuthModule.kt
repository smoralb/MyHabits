package com.smb.ft_auth.domain.di

import com.google.firebase.auth.FirebaseAuth
import com.smb.ft_auth.data.repository.AuthRepositoryImpl
import com.smb.ft_auth.data.source.AuthRemoteDataSource
import com.smb.ft_auth.data.source.AuthRemoteDataSourceImpl
import com.smb.ft_auth.domain.repository.AuthRepository
import com.smb.ft_auth.domain.usecase.CreateNewAccountUseCase
import com.smb.ft_auth.domain.usecase.CreateNewAccountUseCaseImpl
import org.koin.dsl.module

val domainAuthModule = module {
    single<AuthRemoteDataSource> { AuthRemoteDataSourceImpl(auth = FirebaseAuth.getInstance()) }
    single<AuthRepository> { AuthRepositoryImpl(source = get()) }
    factory<CreateNewAccountUseCase> { CreateNewAccountUseCaseImpl(repository = get()) }
}