package com.example.habits.data.source

import com.example.habits.domain.model.SampleChildModel
import com.example.core.data.Result

interface SampleDataRemoteSource {

    suspend fun getSampleData(): Result<SampleChildModel>
}