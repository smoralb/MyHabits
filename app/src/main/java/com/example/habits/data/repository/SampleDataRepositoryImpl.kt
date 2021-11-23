package com.example.habits.data.repository

import com.example.habits.data.source.SampleDataRemoteSource
import com.example.habits.domain.model.SampleChildModel
import com.example.habits.domain.repository.SampleDataRepository
import com.example.core.data.Result

class SampleDataRepositoryImpl(private val remoteSource: SampleDataRemoteSource): SampleDataRepository {

    override suspend fun getSampleData(): Result<SampleChildModel> = remoteSource.getSampleData()

}