package com.smb.ft_main.data.repository

import com.smb.core.data.Result
import com.smb.ft_main.data.source.HomeRemoteSource
import com.smb.ft_main.domain.model.HabitListModel
import com.smb.ft_main.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val remoteSource: HomeRemoteSource
) : HomeRepository {

    override suspend fun getSampleData(): Result<HabitListModel> = remoteSource.getSampleData()

}