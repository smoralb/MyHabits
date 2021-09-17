package com.example.baseapplication.data.repository

import com.example.core.data.Result
import com.example.baseapplication.data.source.SampleDataRemoteSource
import com.example.baseapplication.domain.model.SampleDataModel

class SampleDataRepositoryImpl(private val remoteSource: SampleDataRemoteSource): SampleDataRepository {

    override suspend fun getSampleData(): Result<List<SampleDataModel>> = remoteSource.getSampleData()

}