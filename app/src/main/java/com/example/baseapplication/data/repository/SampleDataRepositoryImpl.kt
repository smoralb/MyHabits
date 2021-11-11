package com.example.baseapplication.data.repository

import com.example.core.data.Result
import com.example.baseapplication.data.source.SampleDataRemoteSource
import com.example.baseapplication.domain.model.SampleDataModel
import com.example.baseapplication.domain.repository.SampleDataRepository

class SampleDataRepositoryImpl(private val remoteSource: SampleDataRemoteSource): SampleDataRepository {

    override suspend fun getSampleData(): Result<SampleDataModel> = remoteSource.getSampleData()

}