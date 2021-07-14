package com.example.baseapplication.data.source

import com.example.core.data.Result
import com.example.baseapplication.domain.model.SampleDataModel

interface SampleDataRemoteSource {

    suspend fun getSampleData(): com.example.core.data.Result<SampleDataModel>
}