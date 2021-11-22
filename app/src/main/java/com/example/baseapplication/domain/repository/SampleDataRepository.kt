package com.example.baseapplication.domain.repository

import com.example.baseapplication.domain.model.SampleChildModel
import com.example.core.data.Result

interface SampleDataRepository {

    suspend fun getSampleData(): Result<SampleChildModel>
}