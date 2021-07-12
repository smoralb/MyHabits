package com.example.baseapplication.data.source

import com.example.baseapplication.data.SampleApi
import com.example.baseapplication.data.core.Result
import com.example.baseapplication.data.core.safeApiCall
import com.example.baseapplication.data.repository.mapper.SampleDataMapper
import com.example.baseapplication.domain.model.SampleDataModel

class SampleDataRemoteSourceImpl(private val api: SampleApi, private val mapper: SampleDataMapper) :
    SampleDataRemoteSource {

    override suspend fun getSampleData(): Result<SampleDataModel> {
        return safeApiCall (
            { api.getSampleData() },
            { entity -> mapper.toDomainModel(entity) }
        )
    }
}