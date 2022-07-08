package com.smb.ft_main.data.repository

import com.smb.core.data.Result
import com.smb.ft_main.data.source.SampleDataRemoteSource
import com.smb.ft_main.domain.model.HabitListModel
import com.smb.ft_main.domain.repository.SampleDataRepository

class SampleDataRepositoryImpl(
    private val remoteSource: SampleDataRemoteSource
) : SampleDataRepository {

    override suspend fun getSampleData(): Result<HabitListModel> = remoteSource.getSampleData()

}