package com.example.baseapplication.data.repository

import com.example.core.data.Result
import com.example.baseapplication.domain.model.SampleDataModel

interface SampleDataRepository {

    suspend fun getSampleData(): Result<List<SampleDataModel>>
}