package com.example.baseapplication.domain.repository

import com.example.core.data.Result
import com.example.baseapplication.domain.model.SampleDataModel

interface SampleDataRepository {

    suspend fun getSampleData(): Result<List<SampleDataModel>>
}