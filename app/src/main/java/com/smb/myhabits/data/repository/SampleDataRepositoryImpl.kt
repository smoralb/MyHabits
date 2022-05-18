package com.smb.myhabits.data.repository

import com.smb.myhabits.data.source.SampleDataRemoteSource
import com.smb.myhabits.domain.model.SampleChildModel
import com.smb.myhabits.domain.repository.SampleDataRepository
import com.smb.core.data.Result

class SampleDataRepositoryImpl(private val remoteSource: SampleDataRemoteSource): SampleDataRepository {

    override suspend fun getSampleData(): Result<SampleChildModel> = remoteSource.getSampleData()

}