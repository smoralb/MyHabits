package com.example.baseapplication.data.source

import com.example.baseapplication.domain.model.SampleChildModel
import com.example.core.data.Result

interface SampleDataRemoteSource {

    suspend fun getSampleData(): Result<SampleChildModel>
}