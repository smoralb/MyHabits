package com.example.baseapplication.data.source

import com.example.baseapplication.data.core.Result
import com.example.baseapplication.domain.model.SampleDataModel

interface SampleDataRemoteSource {

    suspend fun getSampleData(): Result<SampleDataModel>
}