package com.example.baseapplication.data.repository

import com.example.baseapplication.data.source.SampleDataRemoteSource
import com.example.baseapplication.domain.model.SampleChildModel
import com.example.baseapplication.domain.repository.SampleDataRepository
import com.example.core.data.Result

class SampleDataRepositoryImpl(private val remoteSource: SampleDataRemoteSource): SampleDataRepository {

    override suspend fun getSampleData(): Result<SampleChildModel> = remoteSource.getSampleData()

}