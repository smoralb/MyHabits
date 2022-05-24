package com.smb.myhabits.data.repository

import com.smb.core.data.Result
import com.smb.myhabits.data.source.SampleDataRemoteSource
import com.smb.myhabits.domain.model.HabitListModel
import com.smb.myhabits.domain.repository.SampleDataRepository

class SampleDataRepositoryImpl(
    private val remoteSource: SampleDataRemoteSource
) : SampleDataRepository {

    override suspend fun getSampleData(): Result<HabitListModel> = remoteSource.getSampleData()

}