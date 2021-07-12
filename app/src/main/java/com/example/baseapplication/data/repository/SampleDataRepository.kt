package com.example.baseapplication.data.repository

import com.example.baseapplication.data.core.Result
import com.example.baseapplication.domain.model.SampleDataModel

interface SampleDataRepository {

    suspend fun getSampleData(): Result<SampleDataModel>
}