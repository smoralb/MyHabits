package com.smb.myhabits.data.source

import com.smb.myhabits.data.repository.mapper.SampleDataMapper
import com.smb.myhabits.domain.model.SampleChildModel
import com.smb.core.data.Result
import com.smb.core.data.safeApiCall

class SampleDataRemoteSourceImpl(
    private val api: com.smb.myhabits.data.SampleApi,
    private val mapper: SampleDataMapper) : SampleDataRemoteSource {

    override suspend fun getSampleData(): Result<SampleChildModel> {
        return safeApiCall(
            { api.getSampleData() },
            { entity -> mapper.toDomainModel(entity) }
        )
    }
}