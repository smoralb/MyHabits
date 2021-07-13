package com.example.baseapplication.data.repository

import com.example.baseapplication.data.core.Result
import com.example.baseapplication.data.entity.SampleApiResponseEntity
import com.example.baseapplication.data.source.SampleDataRemoteSource
import com.example.baseapplication.domain.model.SampleDataModel
import retrofit2.Response

class SampleDataRepositoryImpl(private val remoteSource: SampleDataRemoteSource): SampleDataRepository {

    override suspend fun getSampleData(): Result<SampleDataModel> = remoteSource.getSampleData()

}