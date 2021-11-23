package com.example.habits.domain.repository

import com.example.habits.domain.model.SampleChildModel
import com.example.core.data.Result

interface SampleDataRepository {

    suspend fun getSampleData(): Result<SampleChildModel>
}